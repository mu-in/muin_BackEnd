package dev.muin.backend.domain.User;

import lombok.Getter;

import javax.persistence.*;

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
    private Role role;
}
