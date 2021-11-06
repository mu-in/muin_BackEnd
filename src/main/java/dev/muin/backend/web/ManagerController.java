package dev.muin.backend.web;

import dev.muin.backend.service.ManagerService;
import dev.muin.backend.web.response.AllStocksPerStoreResponse;
import dev.muin.backend.web.response.MyStoreResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/store")
@RestController
public class ManagerController {

    private final ManagerService managerService;

    @GetMapping("/manager/{userUuid}")
    public ResponseEntity<List<MyStoreResponseDto>> getMyStores(@PathVariable("userUuid") String userUuid) {
        List<MyStoreResponseDto> myStores = managerService.getMyStores(userUuid);
        return ResponseEntity.ok(myStores);
    }

    /**
     * Warning! User data is not included in this request.
     * Therefore, FE always must request correct {storeId} matches jwt
     */
    @GetMapping("/{storeId}/stocks")
    public ResponseEntity<AllStocksPerStoreResponse> getStocksPerStore(@PathVariable("storeId") Short storeId) {
        AllStocksPerStoreResponse res = managerService.getStocksPerStore(storeId);
        return ResponseEntity.ok(res);
    }
}
