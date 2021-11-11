package dev.muin.backend.web;

import dev.muin.backend.service.PaymentService;
import dev.muin.backend.web.request.PaymentRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/payment")
    public ResponseEntity<Map<String,String>> recordPayment(@RequestBody PaymentRequest paymentRequest) throws Exception{
        // 재고 업데이트
        String res = paymentService.saveRecord(paymentRequest);
        return ResponseEntity.ok(Map.of("result", res));
    }
}
