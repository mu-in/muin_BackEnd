package dev.muin.backend.web.response;

import dev.muin.backend.service.dto.RecentPaymentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class AllPaymentsResponse {
    private Short id; //매장 pk
    private List<RecentPaymentDto> payments; //결제내역
}
