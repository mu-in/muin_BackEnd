package dev.muin.backend.web;

import dev.muin.backend.service.StoreService;
import dev.muin.backend.web.response.NearbyStoresResponse;
import dev.muin.backend.web.response.StoreForUserResponse;
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
	public ResponseEntity<List<NearbyStoresResponse>> getNearbyStores(@RequestParam double lat,
																	  @RequestParam double lon, @RequestParam double distance) {
		List<NearbyStoresResponse> res = storeService.getNearbyStores(lat, lon, distance);
		return ResponseEntity.ok(res);
	}

	/**
	 * Get data of certain store
	 */
	@GetMapping("/store/{storeId}")
	public ResponseEntity<StoreForUserResponse> getStore(@PathVariable("storeId") short storeId) throws Exception{
		StoreForUserResponse res = storeService.getStore(storeId);
		return ResponseEntity.ok(res);
	}
}