package dev.muin.backend.web.response;

import dev.muin.backend.service.dto.StockAndProductDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class StockByManagerResponse {
    private int id;       //재고 pk
    private String name;    //상품 이름
    private int quantity;  //재고 수량
    private int price;      //재고 가격

    public StockByManagerResponse(StockAndProductDto stockOrProductDto) {
        this.id = stockOrProductDto.getId();
        this.name = stockOrProductDto.getName();
        this.quantity = stockOrProductDto.getQuantity();
        this.price = stockOrProductDto.getPrice();
    }
}