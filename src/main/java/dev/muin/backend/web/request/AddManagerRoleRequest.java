package dev.muin.backend.web.request;

import dev.muin.backend.domain.Store.Keyword;
import dev.muin.backend.domain.Store.Location;
import dev.muin.backend.domain.Store.Store;
import dev.muin.backend.domain.Store.StoreUUID;
import dev.muin.backend.domain.User.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Setter
@Getter
public class AddManagerRoleRequest {

    @NonNull
    private String userUuid;
    private Double storeLat;
    private Double storeLon;
    private String storeName;
    private String address;
    private List<String> keywords;
    @NonNull
    private String storeUuid;

    public Store toStoreEntity(User member, StoreUUID storeUUID) {
        return Store.builder()
                .name(storeName)
                .location(new Location(storeLat, storeLon, address))
                .keywords(keywords.stream()
                        .map(Keyword::fromString)
                        .collect(Collectors.toList()))
                .manager(member)
                .storeUuid(storeUUID)
                .build();
    }
}
