package dev.muin.backend.web.response;

import dev.muin.backend.domain.Store.Keyword;
import dev.muin.backend.domain.Store.Store;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class StoreResponseDto {
    private String name;
    private String address;
    private List<String> keywords;
    private List<StockResponseDto> stocks;

    public StoreResponseDto(Store store) {
        this.name = store.getName();
        this.address = store.getLocation().getAddress();
        this.keywords = store.getKeywords().stream()
                .map(Keyword::getName)
                .collect(Collectors.toList()); // TODO: 잘 가져오는지 확인
        this.stocks = store.getStocks()
                .stream()
                .map(StockResponseDto::new)
                .collect(Collectors.toList());
    }

}
