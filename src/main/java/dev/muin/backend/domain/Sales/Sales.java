package dev.muin.backend.domain.Sales;

import dev.muin.backend.domain.Store.Store;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="sales_id")
    private short id;

    private short month;

    private int sales;

    @OneToOne(mappedBy="sales")
    private Store store;
}
