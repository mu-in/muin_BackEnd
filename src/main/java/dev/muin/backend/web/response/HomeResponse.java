package dev.muin.backend.web.response;

import dev.muin.backend.service.dto.SalesAnalysisDto;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class HomeResponse {
    private Short id; //매장 pk
    private SalesAnalysisDto sales; //매출 금액
}
