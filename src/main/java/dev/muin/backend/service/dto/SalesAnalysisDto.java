package dev.muin.backend.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class SalesAnalysisDto {
    private List<RecentPaymentDto> recentPayments;
    private int month;// 이번달 매출
    private int today;// 오늘 매출(현재시각까지)
    private List<MonthPaymentDto> statistics; //근 6개월(이번달 제외)
    private StockStatusDto stockStatus;
}