package com.devroulette.restapi.entity;

import com.devroulette.restapi.constant.ErrorMessages;
import com.mysema.commons.lang.Assert;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@MappedSuperclass
@NoArgsConstructor
@RequiredArgsConstructor
public abstract class AbstractTransactableEntity extends AbstractEntity {
    @NonNull
    private long balance;

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
