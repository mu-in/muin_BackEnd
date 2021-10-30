package dev.muin.backend.web.response;

import dev.muin.backend.domain.Stock.Stock;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
public class StockResponseDto {
    private String category;
    private String name;
    private int price;
    private long quantity;

    public StockResponseDto(@NotNull Stock stock) {
        this.category = stock.getProduct().getCategory().getName();
        this.name = stock.getProduct().getName();
        this.price = stock.getProduct().getPrice();
        this.quantity = stock.getQuantity();
    }
}
