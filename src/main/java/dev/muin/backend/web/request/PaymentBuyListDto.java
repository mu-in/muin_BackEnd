package dev.muin.backend.web.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class PaymentBuyListDto {
    private Short id; // 상품 pk
    private String name;
    private Integer quantity;
}
