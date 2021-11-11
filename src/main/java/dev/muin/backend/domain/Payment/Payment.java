package dev.muin.backend.domain.Payment;

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
     * contains stock's [name, quantity, each price] // TODO JSON형으로 저장하자
     */
    private String buyList;
    private int totalPrice; // TODO totalPrice 추출하는 메서드 만들기
    private LocalDateTime payTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;
}
