package dev.muin.backend.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.muin.backend.service.StoreService;
import dev.muin.backend.web.response.NearbyStoreResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class StoreController {

	private final StoreService storeService;

	@GetMapping("/stores/location")
	public ResponseEntity<List<NearbyStoreResponseDto>> getNearbyStores(@RequestParam double lat,
			@RequestParam double lon, @RequestParam double distance) {
		List<NearbyStoreResponseDto> res = storeService.getNearbyStores(lat, lon, distance);
		return ResponseEntity.ok(res);
	}
}