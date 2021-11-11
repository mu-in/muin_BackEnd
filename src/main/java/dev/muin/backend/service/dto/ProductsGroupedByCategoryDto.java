package dev.muin.backend.service.dto;

import dev.muin.backend.domain.Product.Category;
import dev.muin.backend.web.response.ProductByManagerResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * products : actually means 'Stock'
 */
@Getter
@AllArgsConstructor
public class ProductsGroupedByCategoryDto {
    private String category;
    private List<ProductByManagerResponse> products;

    public static ProductsGroupedByCategoryDto generateProductDto(Map.Entry<Category, List<StockAndProductDto>> entry) {
        return new ProductsGroupedByCategoryDto(entry.getKey().getName(), entry.getValue().stream()
                .map(t -> t.toProductDto())
                .collect(Collectors.toList()));
    }
}
