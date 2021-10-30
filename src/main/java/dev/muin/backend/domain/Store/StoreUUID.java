package dev.muin.backend.domain.Store;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.UUID;

@Getter
@Entity
public class StoreUUID {
    @Id
    @Column(length = 40, name="uuid_id")
    private String uuid = UUID.randomUUID().toString();

    @OneToOne(mappedBy = "uuid")
    private Store store;
}
