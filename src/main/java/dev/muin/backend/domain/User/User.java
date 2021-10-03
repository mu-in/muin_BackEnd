package dev.muin.backend.domain.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.muin.backend.domain.Payment.Payment;
import dev.muin.backend.domain.Store.Store;
import dev.muin.backend.oauth2.AuthProvider;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;


/**
 * @see "spring security 적용하면서 id 타입 long으로 바꿈"
 */
@NoArgsConstructor
@Getter
@Entity
public class User {
    @Transient
    private final String DEFAULT_PROVIDER="google";

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="user_id")
    private long id;

    @Column(length = 40, unique = true)
    private String uuid;

    @Email
    @Column(length = 40, unique = true, nullable = false)
    private String email;

    private String imageUrl;

    @JsonIgnore
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    @Column(length = 40, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(length=10)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Store> stores;

    @OneToMany(mappedBy = "user")
    private List<Payment> payments;

    private String providerId = DEFAULT_PROVIDER;

    @Builder(builderClassName= "social", builderMethodName = "socialBuilder")
    private User(String name, @Email String email, String imageUrl, @NotNull AuthProvider provider, String providerId) {
        this.name = name;
        this.email = email;
        this.imageUrl = imageUrl;
        this.provider = provider;
        this.providerId = providerId;
    }

    @Builder(builderClassName = "local",builderMethodName = "localBuilder")
    public User(String name, @Email String email, String imageUrl, String password, @NotNull AuthProvider provider, String providerId) {
        this.name = name;
        this.email = email;
        this.imageUrl = imageUrl;
        this.password = password;
        this.provider = provider;
        this.providerId = providerId;
    }

    public void updateNameAndImage(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }
}
