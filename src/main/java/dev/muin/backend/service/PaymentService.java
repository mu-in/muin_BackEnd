package dev.muin.backend.service;

import dev.muin.backend.domain.Payment.PaymentRepository;
import dev.muin.backend.domain.Stock.Stock;
import dev.muin.backend.domain.Stock.StockRepository;
import dev.muin.backend.domain.Store.Store;
import dev.muin.backend.domain.Store.StoreUUID;
import dev.muin.backend.domain.Store.StoreUUIDRepository;
import dev.muin.backend.domain.User.User;
import dev.muin.backend.domain.User.UserRepository;
import dev.muin.backend.web.request.PaymentBuyListDto;
import dev.muin.backend.web.request.PaymentRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
            Stock stock = stockRepository.findByStoreIdAndProductId(store.getId(), dto.getId()).orElse(null);
            log.info(String.valueOf(stock));
            if(stock == null || stock.getQuantity()<=0) throw new IllegalArgumentException("Stock is not enough");
            stockRepository.updateStockByPayment(store.getId(), dto.getId(), dto.getQuantity());
        }
        //기록
        paymentRepository.save(paymentRequest.toEntity(store));
        return RESPONSE_SUCCESS;
    }
}

