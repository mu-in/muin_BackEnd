package dev.muin.backend.web.response;

import dev.muin.backend.domain.Store.Store;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
@Setter
@Getter
public class NearbyStoresResponseDto {
    private short id;
    private String name;
    private double distance; // 매사거리
    private String address;

    public NearbyStoresResponseDto(Object[] ob) {
        try {
            Store store = (Store) ob[0];
            this.id = store.getId();
            this.name = store.getName();
            this.distance = (double) ob[1];
            this.address = store.getLocation().getAddress();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
