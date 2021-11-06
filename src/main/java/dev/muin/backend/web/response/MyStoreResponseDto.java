package dev.muin.backend.web.response;

import dev.muin.backend.domain.Store.Store;
import lombok.Getter;


@Getter
public class MyStoreResponseDto {
    
    private Short id;
    private String name;
    private String address;
    private String managerName;

    public MyStoreResponseDto(Store store) {
        this.id = store.getId();
        this.name = store.getName();
        this.address = store.getLocation().getAddress();
        this.managerName = store.getManager().getName();
    }
}
