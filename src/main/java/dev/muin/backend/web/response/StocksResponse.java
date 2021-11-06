package dev.muin.backend.web.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.muin.backend.domain.Product.Category;
import dev.muin.backend.domain.Stock.Stock;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
public class StocksResponse {
    private int id;       //재고 pk
    private String name;    //상품 이름
    private int quantity;  //재고 수량
    private int price;      //재고 가격
    @JsonIgnore
    private Category category;

    public StocksResponse(Stock stock){
        this.id =stock.getId();
        this.name = stock.getProduct().getName();
        this.quantity = stock.getQuantity();
        this.price = stock.getProduct().getPrice();
        this.category = stock.getProduct().getCategory();
    }
}
