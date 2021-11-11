package dev.muin.backend.service.dto;

import dev.muin.backend.domain.Sales.Sales;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MonthPaymentDto {
    private LocalDate month; //월
    private int sales; //매출

    public MonthPaymentDto(Sales s){
        this.month = s.getMonth();
        this.sales = s.getSales();
    }
}