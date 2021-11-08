package dev.muin.backend.web;

import dev.muin.backend.service.ManagerService;
import dev.muin.backend.web.response.AllStocksPerStoreForManagerResponse;
import dev.muin.backend.web.response.MyStoreResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/store")
@RestController
public class ManagerController {

    private final ManagerService managerService;

    @GetMapping("/manager/{userUuid}")
    public ResponseEntity<List<MyStoreResponse>> getMyStores(@PathVariable("userUuid") String userUuid) throws Exception{
        List<MyStoreResponse> myStores = managerService.getMyStores(userUuid);
        return ResponseEntity.ok(myStores);
    }

    /**
     * Warning! User data is not included in this request.
     * Therefore, FE always must request correct {storeId} matches jwt
     */
    @GetMapping("/{storeId}/stocks")
    public ResponseEntity<AllStocksPerStoreForManagerResponse> getStocksPerStore(@PathVariable("storeId") Short storeId) throws Exception{
        AllStocksPerStoreForManagerResponse res = managerService.getStocksPerStore(storeId);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/{storeId}/stock/{stockId}")
    public ResponseEntity<Map<String, String>> updateStock(@PathVariable("storeId") Short storeId,
           @PathVariable("stockId") int stockId, @RequestBody Map<String, Integer> quantityMap) throws Exception{
        String res = managerService.updateStock(storeId, stockId, quantityMap.get("quantity"));
        Map<String, String> map = Map.of("result", res);
        return ResponseEntity.ok(map);
    }
}
