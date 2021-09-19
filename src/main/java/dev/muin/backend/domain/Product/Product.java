package dev.muin.backend.domain.Product;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private short id;
    private String barcode;
    private String name;
    private int price;
    private Category category;
}
