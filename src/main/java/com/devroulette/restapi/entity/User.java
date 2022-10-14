package com.devroulette.restapi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "USERS")
public class User extends AbstractEntity {
    @NonNull
    private String username;
    @NonNull
    private String email;
    @NonNull
    private Boolean premium;
    @NonNull
    private Long balance;

    public void pay(Long amount) throws IllegalArgumentException {
        if (this.balance >= amount) {
            this.balance -= amount;
            System.out.println("Balance decreased by: " + amount);
        } else {
            throw new IllegalArgumentException("Not enough balance");
        }
    }

    public void transfer(Long amount) {
        this.balance += amount;
        System.out.println("Balance increased by: " + amount);
    }
}
