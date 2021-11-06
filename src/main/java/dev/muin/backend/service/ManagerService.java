package dev.muin.backend.service;

import dev.muin.backend.domain.Store.StoreRepository;
import dev.muin.backend.domain.User.UserRepository;
import dev.muin.backend.web.response.MyStoreResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ManagerService {

    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    @Transactional(readOnly = true)
    public List<MyStoreResponseDto> getMyStores(String userUuid) {
        List<MyStoreResponseDto> res = storeRepository.findByUserId(userUuid);
        return res;
    }
}
