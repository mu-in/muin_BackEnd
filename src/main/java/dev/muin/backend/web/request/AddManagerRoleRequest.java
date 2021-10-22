package dev.muin.backend.web.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class AddManagerRoleRequest {

    @NonNull
    private String userUuid;
    private Double storeLat;
    private Double storeLon;
    @NonNull
    private String storeUuid;
}
