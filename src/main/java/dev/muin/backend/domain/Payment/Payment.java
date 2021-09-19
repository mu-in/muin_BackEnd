package dev.muin.backend.domain.Payment;

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

}
