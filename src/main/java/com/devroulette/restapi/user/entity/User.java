package com.devroulette.restapi.user.entity;

import com.devroulette.restapi.common.entity.AbstractTransactableEntity;
import com.devroulette.restapi.roulette.bets.entity.UserBet;
import com.devroulette.restapi.user.bots.entity.Bot;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "USERS")
public class User extends AbstractTransactableEntity implements UserDetails {
    @NonNull
    private String username;
    @NonNull
    private String role;

    // TODO Cascade settings
    @OneToMany(mappedBy = "user")
    private List<Bot> bots;

    @OneToMany(mappedBy = "user")
    private List<UserBet> userBets;

    public User(String username, long balance, String role) {
        super(balance);
        this.username = username;
        this.role = role;
    }

    public void addBot(Bot bot) {
        if (Objects.isNull(this.bots)) {
            this.bots = new ArrayList<>();
        }

        this.bots.add(bot);
        bot.setUser(this);
    }

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
}
