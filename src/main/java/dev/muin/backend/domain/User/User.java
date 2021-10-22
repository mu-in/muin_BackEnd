package dev.muin.backend.domain.User;

import dev.muin.backend.domain.Payment.Payment;
import dev.muin.backend.domain.Store.Store;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;



@NoArgsConstructor
@Getter
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private short id;

    @Column(length = 40, unique = true)
    private String uuid;

    @Email
    @Column(length = 40, unique = true, nullable = false)
    private String email;

    @Column(length = 40, nullable = false)
    private String name;

    /**
     * 기본으로 USER를 가짐
     */
    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Store> stores;

    @OneToMany(mappedBy = "user")
    private List<Payment> payments;

    public void updateToManager(){
        this.role = Role.MANAGER;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> res = new ArrayList<>();
        res.add(new SimpleGrantedAuthority(role.toString()));
        return res;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Builder
    public User(String uuid, String email, String name, Role role){
        this.uuid = uuid;
        this.email = email;
        this.name = name;
        this.role = role;
    }
}
