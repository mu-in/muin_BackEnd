package dev.muin.backend.domain.User;

import dev.muin.backend.domain.Payment.Payment;
import dev.muin.backend.domain.Store.Store;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="user_id")
    private short id;

    @Column(length = 40, unique = true)
    private String uuid;

    @Column(length = 40, unique = true)
    private String email;

    @Column(length = 40)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(length=10)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Store> stores;

    @OneToMany(mappedBy = "user")
    private List<Payment> payments;
}
