package dev.muin.backend.service.dto;

import dev.muin.backend.domain.Payment.Payment;
import lombok.Getter;
import org.json.simple.parser.ParseException;

import java.time.LocalDateTime;

@Getter
public class RecentPaymentDto {
    LocalDateTime time;
    int price;

    public RecentPaymentDto(Payment p) throws ParseException {
        this.time = p.getPayTime();
        this.price = p.getTotalPrice();
    }
}