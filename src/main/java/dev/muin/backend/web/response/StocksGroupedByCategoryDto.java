package dev.muin.backend.web.response;

import dev.muin.backend.domain.Product.Category;
import dev.muin.backend.service.dto.StockAndProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Convert Category from map to Key-Value format
 * <pre>
 * <h3>Before</h3>
 * {'과자':[stock list]}
 *  <h3>After</h3>
 *
 * {'category': '과자','stocks':[...]}
 * </pre>
 */
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
