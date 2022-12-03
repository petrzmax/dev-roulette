package com.devroulette.restapi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Collections;

@Entity
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "USERS")
public class User extends AbstractEntity implements UserDetails {
    @NonNull
    private String username;
    @NonNull
    private boolean premium;
    @NonNull
    private long balance;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void transfer(long amount) {
        this.balance += amount;
        System.out.println("Balance increased by: " + amount);
    }

    public void pay(long amount) throws IllegalArgumentException {
        if (this.balance >= amount) {
            this.balance -= amount;
            System.out.println("Balance decreased by: " + amount);
        } else {
            throw new IllegalArgumentException("Not enough balance");
        }
    }
}
