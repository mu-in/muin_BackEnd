package dev.muin.backend.service;

import dev.muin.backend.domain.Payment.PaymentRepository;
import dev.muin.backend.domain.Store.Store;
import dev.muin.backend.domain.Store.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class SalesScheduler {

    private final PaymentRepository paymentRepository;
    private final StoreRepository storeRepository;

    @Scheduled(cron="0 0 0 1 * *") //매월 1일 0시
    public void getMonthlySales(){
        List<Store> stores = storeRepository.findAll();
        for(Store s: stores) {
            log.info("store+(id="+s.getId()+")is start to get monthly sales....");
            paymentRepository.findThisMonthByStore(s.getId());
        }
    }

    @Scheduled(cron="0 0 0 13 11 *") //매월 13일 (코드 생성일자:12일)
    public void test(){
        List<Store> stores = storeRepository.findAll();
        for(Store s: stores) {
            log.info("store+(id="+s.getId()+")is start to get monthly sales....");
            paymentRepository.findThisMonthByStore(s.getId());
        }
    }
}
