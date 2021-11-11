package dev.muin.backend.domain.Sales;

import dev.muin.backend.domain.Store.Store;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sales_id")
    private short id;

    private LocalDate month;

    private int sales;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;
}
