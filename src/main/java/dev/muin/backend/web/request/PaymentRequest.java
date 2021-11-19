package dev.muin.backend.web.request;

import com.google.gson.Gson;
import dev.muin.backend.domain.Payment.Payment;
import dev.muin.backend.domain.Store.Store;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;


@ToString
@Setter
@Getter
public class PaymentRequest {
    private String storeUuid;
    private List<PaymentBuyListDto> stocks;
    private Integer totalPrice;
    private LocalDateTime payTime; //yyyy-mm-ddTHH:mm:ss

    public Payment toEntity(Store store) {
        return Payment.builder()
                .buyList(new Gson().toJson(stocks))
                .totalPrice(totalPrice)
                .payTime(payTime)
                .store(store)
                .build();
    }
}
