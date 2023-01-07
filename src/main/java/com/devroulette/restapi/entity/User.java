package com.devroulette.restapi.entity;

import com.devroulette.restapi.constant.ErrorMessages;
import com.mysema.commons.lang.Assert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
    private long balance;
    @NonNull
    private String role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(this.role));
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
        Assert.isTrue(amount > 0, ErrorMessages.NEGATIVE_AMOUNT);

        this.balance += amount;
        System.out.println("Balance increased by: " + amount);
    }

    public void pay(long amount) throws IllegalArgumentException {
        Assert.isTrue(amount > 0, ErrorMessages.NEGATIVE_AMOUNT);
        Assert.isTrue(this.balance >= amount, ErrorMessages.NOT_ENOUGH_BALANCE);

        this.balance -= amount;

        System.out.println("Balance decreased by: " + amount);
    }
}
