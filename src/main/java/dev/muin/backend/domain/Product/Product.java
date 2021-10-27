package dev.muin.backend.domain.Product;

import dev.muin.backend.domain.Stock.Stock;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
public class Product {
    @Id
    @Column(name = "product_id")
    private short id;

    private String name;

    private int price;

    @Enumerated(EnumType.STRING)
    @Column(length=15)
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<Stock> stocks;
}
