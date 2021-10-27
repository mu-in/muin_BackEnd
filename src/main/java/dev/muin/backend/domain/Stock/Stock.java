package dev.muin.backend.domain.Stock;

import dev.muin.backend.domain.Product.Product;
import dev.muin.backend.domain.Store.Store;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
