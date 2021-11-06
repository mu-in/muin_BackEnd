package dev.muin.backend.web.response;

import dev.muin.backend.domain.Product.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Getter
public class AllStocksPerStoreResponse {
    private Short id;
    private Map<Category, List<StocksResponse>> products;
}
