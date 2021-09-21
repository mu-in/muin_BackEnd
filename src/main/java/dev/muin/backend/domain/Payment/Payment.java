package dev.muin.backend.domain.Payment;

import dev.muin.backend.domain.Stock;
import dev.muin.backend.domain.User.User;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="payment_id")
    private short id;

    private short quantity;

    private LocalDateTime payTime;

    private int price;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="stock_id")
    private Stock stock;
}
