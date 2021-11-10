package dev.muin.backend.web.response;

import dev.muin.backend.service.dto.StockAndProductDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ProductByManagerResponse {
    private int id;       //상품 pk
    private String name;    //상품 이름
    private int price;      //상품 가격

    public ProductByManagerResponse(StockAndProductDto stockOrProductDto) {
        this.id = stockOrProductDto.getId();
        this.name = stockOrProductDto.getName();
        this.price = stockOrProductDto.getPrice();
    }
}
