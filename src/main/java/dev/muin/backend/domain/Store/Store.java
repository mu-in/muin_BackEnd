package dev.muin.backend.domain.Store;

import dev.muin.backend.domain.Sales.Sales;
import dev.muin.backend.domain.Stock;
import dev.muin.backend.domain.Tag.Tag;
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

    @Column(length = 36)
    private String uuid;

    @Column(length=10)
    private String name;

    @Embedded
    private Location location;

    @JoinTable(
        name = "store_tag",
        joinColumns = @JoinColumn(name = "store_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id"))
    @ManyToMany
    private List<Tag> tags;

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
