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
    private String uuid;
    private String email;
    private String name;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Store> stores;

    @OneToMany(mappedBy = "user")
    private List<Payment> payments;
}
