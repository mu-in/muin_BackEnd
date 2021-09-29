package dev.muin.backend.domain.Store;

import dev.muin.backend.domain.Sales.Sales;
import dev.muin.backend.domain.Stock.Stock;
import dev.muin.backend.domain.User.User;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;


@Getter
@Entity
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="store_id")
    private short id;

    @Column(length = 40, unique = true)
    private String uuid;

    @Column(length=10)
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
    @JoinColumn(name = "sales_id")
    private Sales sales;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy = "store")
    private List<Stock> stocks;

    @Override
    public String toString() {
        return String.format("{id=%d,uuid=%d,location=(%lf,%lf),name=%s,keyword=%s");
    }
}
