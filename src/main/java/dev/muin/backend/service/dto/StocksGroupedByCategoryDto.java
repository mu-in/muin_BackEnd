package dev.muin.backend.service.dto;

import dev.muin.backend.domain.Product.Category;
import dev.muin.backend.web.response.StockByManagerResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class StocksGroupedByCategoryDto {
    private String category;
    private List<StockByManagerResponse> stocks;

    public static StocksGroupedByCategoryDto generateStockDto(Map.Entry<Category, List<StockAndProductDto>> entry) {
        return new StocksGroupedByCategoryDto(entry.getKey().getName(), entry.getValue().stream()
                .map(t -> t.toStockDto())
                .collect(Collectors.toList()));
    }
}
