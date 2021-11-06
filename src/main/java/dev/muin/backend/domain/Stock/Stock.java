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
    @Column(name = "stock_id")
    private int id;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    /**
     * Unique value at a series of same "store_id"
     */
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
