package dev.muin.backend.service;

import dev.muin.backend.domain.Payment.PaymentRepository;
import dev.muin.backend.domain.Sales.Sales;
import dev.muin.backend.domain.Sales.SalesRepository;
import dev.muin.backend.domain.Store.Store;
import dev.muin.backend.domain.Store.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class SalesScheduler {

    private final PaymentRepository paymentRepository;
    private final StoreRepository storeRepository;
    private final SalesRepository salesRepository;

    @Scheduled(cron="0 0 0 1 * *") //매월 1일 0시
    public void getMonthlySales(){
        List<Store> stores = storeRepository.findAll();
        log.info("Store is start to get monthly sales.......");
        for(Store s: stores) {
            Integer monthlySales = paymentRepository.findPrevMonthByStore(s.getId(),  Date.from(ZonedDateTime.now(ZoneId.of("Asia/Seoul")).toInstant()));
            if(monthlySales == null) monthlySales = 0;
            Sales sales = new Sales(LocalDate.now().minusMonths(1l), monthlySales, s); // get previous month's
            salesRepository.save(sales);
            log.info(String.format("store(id=%d,name=%s)'s sales=%d", s.getId(), s.getName(), monthlySales));
        }
        log.info("End to get monthly sales.......");
    }

}
