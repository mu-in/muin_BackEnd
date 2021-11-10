package dev.muin.backend.web.response;

import dev.muin.backend.domain.Store.Keyword;
import dev.muin.backend.domain.Store.Store;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @see /store/{storeId}
 */
@Getter
public class StoreByUserResponse {
    private String name;
    private String address;
    private List<String> keywords;
    private List<StockByUserResponse> stocks;

    public StoreByUserResponse(Store store) {
        this.name = store.getName();
        this.address = store.getLocation().getAddress();
        this.keywords = store.getKeywords().stream()
                .map(Keyword::getName)
                .collect(Collectors.toList()); // TODO: 잘 가져오는지 확인
        this.stocks = store.getStocks()
                .stream()
                .map(StockByUserResponse::new)
                .collect(Collectors.toList());
    }

}
