package dev.muin.backend.service;

import dev.muin.backend.domain.Store.StoreRepository;
import dev.muin.backend.web.response.NearbyStoreResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class StoreService {

    private final StoreRepository storeRepository;

    /**
     * @param distance 0.5, 1, 2(km)
     */
    @Transactional
    public List<NearbyStoreResponseDto> getNearbyStores(double userLat, double userLon, double distance) {
        List<Object[]> res = storeRepository.findAllByDistanceASC(userLat, userLon, distance);
        List<NearbyStoreResponseDto> result = res
                .stream()
                .map(NearbyStoreResponseDto::new)
                .collect(Collectors.toList());
        return result;
    }
}
