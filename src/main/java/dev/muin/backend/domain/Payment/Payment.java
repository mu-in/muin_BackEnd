package dev.muin.backend.domain.Payment;

import dev.muin.backend.domain.Stock.Stock;
import dev.muin.backend.domain.Store.Store;
import dev.muin.backend.domain.User.User;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private int id;

    /**
     * contains stock's {name, quantity, each price}, total price
     */
    private String buyList;
    private LocalDateTime payTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;
}
