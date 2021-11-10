package dev.muin.backend.service;

import dev.muin.backend.domain.Store.Store;
import dev.muin.backend.domain.Store.StoreRepository;
import dev.muin.backend.web.response.NearbyStoresResponse;
import dev.muin.backend.web.response.StoreForUserResponse;
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
    public List<NearbyStoresResponse> getNearbyStores(double userLat, double userLon, double distance) {
        List<Object[]> res = storeRepository.findAllByDistanceASC(userLat, userLon, distance);
        List<NearbyStoresResponse> result = res
                .stream()
                .map(NearbyStoresResponse::new)
                .collect(Collectors.toList());
        return result;
    }

    @Transactional(readOnly = true)
    public StoreForUserResponse getStore(short storeId) throws NullPointerException{
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new NullPointerException("Store Not Found"));
        StoreForUserResponse res = new StoreForUserResponse(store);
        return res;
    }
}
