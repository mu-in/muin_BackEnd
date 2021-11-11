package dev.muin.backend.domain.Store;

import dev.muin.backend.domain.Payment.Payment;
import dev.muin.backend.domain.Sales.Sales;
import dev.muin.backend.domain.Stock.Stock;
import dev.muin.backend.domain.User.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private short id;

    @Column(length = 20)
    private String name;

    @Embedded
    private Location location;

    @ElementCollection
    @CollectionTable(
            name = "store_keyword",
            joinColumns = @JoinColumn(name = "store_id")
    )
    @Enumerated(EnumType.STRING)
    @Column(length = 15)
    private List<Keyword> keywords;

    @OneToOne
    @JoinColumn(name = "uuid_id")
    private StoreUUID uuid;

    @OneToMany(mappedBy = "store")
    private List<Sales> sales;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User manager;

    @OneToMany(mappedBy = "store")
    private List<Stock> stocks;

    @OneToMany(mappedBy = "store")
    private List<Payment> payments;

    public void updateManger(@NonNull User manager) {
        this.manager = manager;
        manager.getStores().add(this);
    }

    @Builder
    public Store(String name, User manager, Location location, List<Keyword> keywords, StoreUUID storeUuid) {
        this.name = name;
        this.location = location;
        this.keywords = keywords;
        this.manager = manager;
        manager.getStores().add(this);
        this.uuid = storeUuid;
    }

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", location=" + location +
                ", keywords=" + keywords +
                ", sales=" + sales +
                ", user=" + manager +
                ", stocks=" + stocks +
                '}';
    }
}
