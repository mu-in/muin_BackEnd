package dev.muin.backend.web.request;

import dev.muin.backend.domain.Store.Keyword;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;

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
    private List<Keyword> keywords;
    @NonNull
    private String storeUuid;
}
