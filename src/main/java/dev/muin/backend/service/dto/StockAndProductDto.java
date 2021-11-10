package dev.muin.backend.service.dto;

import dev.muin.backend.domain.Product.Category;
import dev.muin.backend.domain.Product.Product;
import dev.muin.backend.domain.Stock.Stock;
import dev.muin.backend.web.response.ProductByManagerResponse;
import dev.muin.backend.web.response.StockByManagerResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
public class StockAndProductDto {
    private int id;       //재고 pk
    private String name;    //상품 이름
    private int quantity;  //재고 수량
    private int price;      //재고 가격
    private Category category;

    public StockAndProductDto(Stock stock){
        this.id =stock.getId();
        this.name = stock.getProduct().getName();
        this.quantity = stock.getQuantity();
        this.price = stock.getProduct().getPrice();
        this.category = stock.getProduct().getCategory();
    }

    public StockAndProductDto(Product product){
        this.id =product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.category = product.getCategory();
    }

    public StockByManagerResponse toStockDto(){
        return new StockByManagerResponse(this);
    }

    public ProductByManagerResponse toProductDto(){
        return new ProductByManagerResponse(this);
    }
}
