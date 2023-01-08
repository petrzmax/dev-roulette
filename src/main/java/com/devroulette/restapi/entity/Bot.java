package com.devroulette.restapi.entity;

import com.devroulette.restapi.constant.ErrorMessages;
import com.mysema.commons.lang.Assert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "BOTS")
public class Bot extends AbstractEntity {
    @NonNull
    private String name;
    @NonNull
    private long balance;


    // TODO Move to abstract class / interface & from user
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
