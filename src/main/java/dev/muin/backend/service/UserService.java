package dev.muin.backend.service;

import dev.muin.backend.config.jwt.JwtTokenProvider;
import dev.muin.backend.domain.Store.Store;
import dev.muin.backend.domain.Store.StoreRepository;
import dev.muin.backend.domain.Store.StoreUUID;
import dev.muin.backend.domain.Store.StoreUUIDRepository;
import dev.muin.backend.domain.User.Role;
import dev.muin.backend.domain.User.User;
import dev.muin.backend.domain.User.UserRepository;
import dev.muin.backend.web.request.AddManagerRoleRequest;
import dev.muin.backend.web.request.LoginRequest;
import dev.muin.backend.web.response.LoginResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;
import java.util.regex.PatternSyntaxException;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final StoreUUIDRepository storeUUIDRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final short QR_VALID_TIME = 30000; //30sec
    private final String QR_SEPARATOR = ":";

    @Transactional(readOnly = true)
    public LoginResponse saveOrUpdate(LoginRequest loginRequest) {
        User member = userRepository.findByEmail(loginRequest.getEmail())
                .orElse(null);
        if (member == null) member = join(loginRequest);
        String jwt = jwtTokenProvider.createToken(member.getEmail());
        return new LoginResponse(jwt, member.getUuid(), member.getRole());
    }

    private User join(LoginRequest loginRequest) throws IllegalArgumentException {
        String uuid = UUID.randomUUID().toString();
        log.info("joined user's uuid: " + uuid);
        User user = User.builder()
                .uuid(uuid)
                .email(loginRequest.getEmail())
                .name(loginRequest.getName())
                .role(Role.USER)
                .build();

        return userRepository.save(user);
    }

    /**
     * Add a "Manager" Role to user. Assume that the store is already registered.
     * Fail Conditions:
     * 1. Invalid userUuid
     * 2. Invalid storeUuid
     * 2. Already exist of store's manager(by you or the other)
     */
    @Transactional
    public String authenticateManager(HttpServletRequest request, AddManagerRoleRequest addManagerRoleRequest)
            throws UsernameNotFoundException, IllegalArgumentException {
        // User validation
        User member = userRepository.findByUuid(addManagerRoleRequest.getUserUuid())
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        if (isSameUserAsJwt(request, member.getEmail()) == false) {
            throw new IllegalArgumentException("Requested wrong user");
        }
        // StoreUuid validation
        Store store = validateAndGetStoreByStoreUUID(addManagerRoleRequest.getStoreUuid(), member);

        store.updateUser(member);
        member.updateToManager();
        return String.format("Certified as a manager of %s", store.getName());
    }

    private boolean isSameUserAsJwt(HttpServletRequest request, String dtoEmail) {
        String jwt = jwtTokenProvider.resolveToken(request);
        String email = jwtTokenProvider.getPk(jwt);
        return email.equals(dtoEmail);
    }

    private Store validateAndGetStoreByStoreUUID(String storeUuid, User member) throws IllegalArgumentException{
        // is exist storeUUID
        StoreUUID sId = storeUUIDRepository.findById(storeUuid).orElse(null);
        if (sId == null) return null;

        // is already registered storeUUID
        Store store = sId.getStore();
        if (store != null) throw new IllegalArgumentException("Serial number is not valid");
        if(store.getUser() != null){
            if (store.getUser().equals(member)) {
                throw new IllegalArgumentException("You are already certified as a manager");
            }
            throw new IllegalArgumentException("Requested store is already owned by other user");
        }
        return sId.getStore();
    }

    public boolean QRauthentication(String seed) throws IllegalArgumentException {
        Date now = new Date();
        Long serverMilli = now.getTime();
        Long deadline;
        try {
            String[] seq = seed.split(QR_SEPARATOR);
            deadline = Long.parseLong(seq[0]) + QR_VALID_TIME;
        } catch (PatternSyntaxException e) {
            throw new IllegalArgumentException("Separator of the value is not exist or invalid");
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Format of the value is invalid");
        }
        log.info(String.format("deadline: %d, serverMilli: %d", deadline, serverMilli));
        return deadline > serverMilli;
    }
}
