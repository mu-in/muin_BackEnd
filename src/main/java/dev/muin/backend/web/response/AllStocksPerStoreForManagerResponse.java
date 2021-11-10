package dev.muin.backend.web.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.muin.backend.domain.Product.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Getter
class ForResponseFormat{
    private String category;
    private List<StockForManagerResponse> stocks;

    public ForResponseFormat(Map.Entry<Category, List<StockForManagerResponse>> entry){
        this.category= entry.getKey().getName();
        this.stocks= entry.getValue();
    }
}
@AllArgsConstructor
@Getter
public class AllStocksPerStoreForManagerResponse {
    private Short id;
    private List<ForResponseFormat> products;

    public AllStocksPerStoreForManagerResponse(Short id, Map<Category, List<StockForManagerResponse>> map){
        this.id = id;
        this.products = map.entrySet().stream()
                .map(ForResponseFormat::new)
                .collect(Collectors.toList());
    }
}
