package dev.muin.backend.domain.Product;

import dev.muin.backend.domain.Stock;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private short id;
    private String name;
    private String barcode;
    private int price;
    @Enumerated(EnumType.STRING)
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<Stock> stocks;
}
