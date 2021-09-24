package dev.muin.backend.domain.Stock;

import dev.muin.backend.domain.Payment.Payment;
import dev.muin.backend.domain.Product.Product;
import dev.muin.backend.domain.Store.Store;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;
    private short quantity;

    @ManyToOne
    @JoinColumn(name="store_id")
    private Store store;

    @OneToMany(mappedBy = "stock")
    private List<Payment> payments;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;
}
