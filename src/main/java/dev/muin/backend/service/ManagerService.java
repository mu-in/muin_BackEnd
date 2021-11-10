package dev.muin.backend.service;

import dev.muin.backend.domain.Product.Category;
import dev.muin.backend.domain.Product.ProductRepository;
import dev.muin.backend.domain.Stock.StockRepository;
import dev.muin.backend.domain.Store.StoreRepository;
import dev.muin.backend.domain.User.UserRepository;
import dev.muin.backend.service.dto.StockAndProductDto;
import dev.muin.backend.web.response.AllStocksPerStoreResponse;
import dev.muin.backend.web.response.MyStoreResponse;
import dev.muin.backend.web.response.ProductsGroupedByCategoryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ManagerService {

    private final StoreRepository storeRepository;
    private final StockRepository stockRepository;
    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<MyStoreResponse> getMyStores(String userUuid) {
        List<MyStoreResponse> res = storeRepository.findByUserId(userUuid);
        return res;
    }

    // TODO request에 user_uuid 넣어달라고 하기
    @Transactional(readOnly = true)
    public AllStocksPerStoreResponse getStocksPerStore(Short storeId) {
        storeRepository.findById(storeId).orElseThrow(() -> new IllegalArgumentException("Store Not Found"));
        List<StockAndProductDto> findRes = stockRepository.findStocksByStoreId(storeId);
        Map<Category, List<StockAndProductDto>> catRes = findRes.stream()
                .collect(Collectors.groupingBy((StockAndProductDto::getCategory)));

        return new AllStocksPerStoreResponse(storeId, catRes);
    }

    @Transactional
    public String updateStock(Short storeId, int stockId, int quantity) throws IllegalArgumentException{
        if(quantity<0) throw new IllegalArgumentException("Cannot set under zero(0)");
        stockRepository.updateStock(storeId, stockId, quantity);
        return "success";
    }

    @Transactional(readOnly = true)
    public List<ProductsGroupedByCategoryDto> getProducts() {
        List<StockAndProductDto> findRes = productRepository.findAllProducts();
        Map<Category, List<StockAndProductDto>> catRes = findRes.stream()
                .collect(Collectors.groupingBy((StockAndProductDto::getCategory)));

        return catRes.entrySet().stream()
                .map(ProductsGroupedByCategoryDto::generateProductDto)
                .collect(Collectors.toList());
    }
}
