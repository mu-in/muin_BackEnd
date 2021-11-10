package dev.muin.backend.web.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class AddNewStockRequest {
    private int productId;
    private int quantity;
}
