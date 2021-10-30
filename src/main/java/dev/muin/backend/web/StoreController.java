package dev.muin.backend.web;

import dev.muin.backend.domain.Product.ProductRepository;
import dev.muin.backend.service.StoreService;
import dev.muin.backend.web.response.NearbyStoresResponseDto;
import dev.muin.backend.web.response.StoreResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class StoreController {

	private final StoreService storeService;

	@GetMapping("/stores/location")
	public ResponseEntity<List<NearbyStoresResponseDto>> getNearbyStores(@RequestParam double lat,
																		 @RequestParam double lon, @RequestParam double distance) {
		List<NearbyStoresResponseDto> res = storeService.getNearbyStores(lat, lon, distance);
		return ResponseEntity.ok(res);
	}

	@GetMapping("/store/{storeId}")
	public ResponseEntity<StoreResponseDto> getStore(@PathVariable("storeId") short storeId) throws Exception{
		StoreResponseDto res = storeService.getStore(storeId);
		return ResponseEntity.ok(res);
	}
}