package dev.muin.backend.web.response;

import dev.muin.backend.domain.Product.Category;
import dev.muin.backend.service.dto.StockAndProductDto;
import dev.muin.backend.service.dto.StocksGroupedByCategoryDto;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * products : actually means 'Stock'
 */
@Getter
public class AllStocksPerStoreResponse {
    private Short id;
    private List<StocksGroupedByCategoryDto> products;

    public AllStocksPerStoreResponse(Short id, Map<Category, List<StockAndProductDto>> map) {
        this.id = id;
        this.products = map.entrySet().stream()
                .map(StocksGroupedByCategoryDto::generateStockDto)
                .collect(Collectors.toList());
    }
}
