package dev.muin.backend.domain.Stock;

import dev.muin.backend.domain.Product.Product;
import dev.muin.backend.domain.Store.Store;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
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

    public Stock(int quantity, Store store, Product product){
        this .quantity = quantity;
        this.store = store;
        this.product = product;
        if(!store.getStocks().contains(this)) store.getStocks().add(this);
        if(!product.getStocks().contains(this)) product.getStocks().add(this);
    }
}
