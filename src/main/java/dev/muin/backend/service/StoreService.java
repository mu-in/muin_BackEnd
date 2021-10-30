package dev.muin.backend.service;

import dev.muin.backend.domain.Store.Store;
import dev.muin.backend.domain.Store.StoreRepository;
import dev.muin.backend.web.response.NearbyStoresResponseDto;
import dev.muin.backend.web.response.StoreResponseDto;
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
    @Transactional(readOnly = true)
    public List<NearbyStoresResponseDto> getNearbyStores(double userLat, double userLon, double distance) {
        List<Object[]> res = storeRepository.findAllByDistanceASC(userLat, userLon, distance);
        List<NearbyStoresResponseDto> result = res
                .stream()
                .map(NearbyStoresResponseDto::new)
                .collect(Collectors.toList());
        return result;
    }

    @Transactional(readOnly = true)
    public StoreResponseDto getStore(short storeId) throws NullPointerException{
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new NullPointerException("Store Not Found"));
        StoreResponseDto res = new StoreResponseDto(store);
        return res;
    }
}
