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
public class NearbyStoreResponseDto {
    private String storeUuid;
    private String name;
    private double distance; // 매사거리
    private String address;
    private String managerName;

    public NearbyStoreResponseDto(Object[] ob) {
        try {
            Store store = (Store) ob[0];
            this.storeUuid = store.getUuid();
            this.name = store.getName();
            this.distance = (double) ob[1];
            this.address = store.getLocation().getAddress();
            if(store.getUser()!=null) this.managerName = store.getUser().getName();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
