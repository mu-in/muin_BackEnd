package dev.muin.backend.service;

import dev.muin.backend.config.jwt.JwtTokenProvider;
import dev.muin.backend.domain.Store.Store;
import dev.muin.backend.domain.Store.StoreRepository;
import dev.muin.backend.domain.User.Role;
import dev.muin.backend.domain.User.User;
import dev.muin.backend.domain.User.UserRepository;
import dev.muin.backend.service.util.UserUtil;
import dev.muin.backend.web.request.AddManagerRoleRequest;
import dev.muin.backend.web.request.LoginRequest;
import dev.muin.backend.web.response.LoginResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional(readOnly = true)
    public LoginResponse saveOrUpdate(LoginRequest loginRequest) {
        User member = userRepository.findByEmail(loginRequest.getEmail())
                .orElse(null);
        if(member == null) member = join(loginRequest);
        String jwt = jwtTokenProvider.createToken(member.getEmail());
        return new LoginResponse(jwt, member.getUuid(), member.getRole());
    }

    @Transactional(readOnly = true)
    public UserResponse myInfo(String uuid) {
        User member = userRepository.findByUuid(uuid).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        return UserResponse.of(member);
    }

    private User join(LoginRequest loginRequest) throws IllegalArgumentException{
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
     * 1. Invalid storeUuid or Invalid userUuid
     * 2. Already exist of store's manager // TODO
     * 3. Already exist of other store's location // TODO
     */
    @Transactional
    public String authenticateManager(AddManagerRoleRequest addManagerRoleRequest)
            throws UsernameNotFoundException, IllegalArgumentException {
        User member = userRepository.findByUuid(addManagerRoleRequest.getUserUuid())
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        Store store = storeRepository.findByUuid(addManagerRoleRequest.getStoreUuid())
                .orElseThrow(() -> new NullPointerException("Store Not Found"));
        if(!UserUtil.isLocationValid(addManagerRoleRequest, store.getLocation())){
            throw new IllegalArgumentException("Requested store is not valid");
        }

        store.updateUser(member);
        member.updateToManager();
        return String.format("Certified as a manager of %s", store.getName());
    }
}
