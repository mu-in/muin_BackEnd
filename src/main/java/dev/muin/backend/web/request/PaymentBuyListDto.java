package dev.muin.backend.web.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class PaymentBuyListDto {
    private Short id;
    private String name;
    private Integer quantity;
}
