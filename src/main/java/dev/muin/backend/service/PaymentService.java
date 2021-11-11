package dev.muin.backend.service;

import dev.muin.backend.domain.Payment.PaymentRepository;
import dev.muin.backend.domain.Stock.StockRepository;
import dev.muin.backend.domain.Store.Store;
import dev.muin.backend.domain.Store.StoreUUID;
import dev.muin.backend.domain.Store.StoreUUIDRepository;
import dev.muin.backend.web.request.PaymentBuyListDto;
import dev.muin.backend.web.request.PaymentRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class PaymentService {
    private final StoreUUIDRepository storeUUIDRepository;
    private final StockRepository stockRepository;
    private final PaymentRepository paymentRepository;
    private final String RESPONSE_SUCCESS = "success";

    @Transactional
    public String saveRecord(PaymentRequest paymentRequest) throws IllegalArgumentException {
        StoreUUID storeUUID = storeUUIDRepository.findById(paymentRequest.getStoreUuid())
                .orElseThrow(() -> new IllegalArgumentException("Store Not Found"));
        Store store = storeUUID.getStore();
        List<PaymentBuyListDto> stocks = paymentRequest.getStocks();

        //수량 업데이트
        for(PaymentBuyListDto dto: stocks) {
            stockRepository.updateStockByPayment(store.getId(), dto.getId(), dto.getQuantity());
        }
        //기록
        paymentRepository.save(paymentRequest.toEntity());
        return RESPONSE_SUCCESS;
    }
}

