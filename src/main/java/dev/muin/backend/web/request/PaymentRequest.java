package dev.muin.backend.web.request;

import com.google.gson.Gson;
import dev.muin.backend.domain.Payment.Payment;
import dev.muin.backend.domain.Store.Store;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;


@ToString
@Setter
@Getter
public class PaymentRequest {
    private String storeUuid;
    private List<PaymentBuyListDto> stocks;
    private Integer totalPrice;

    /**
     * payTime: Because Herorku server is located at USA, fixed ZonedDateTime to sync timezone
     */
    public Payment toEntity(Store store) {
        return Payment.builder()
                .buyList(new Gson().toJson(stocks))
                .totalPrice(totalPrice)
                .payTime(LocalDateTime.ofInstant(ZonedDateTime.now().toInstant(), ZoneId.of("Asia/Seoul")))
                .store(store)
                .build();
    }
}
