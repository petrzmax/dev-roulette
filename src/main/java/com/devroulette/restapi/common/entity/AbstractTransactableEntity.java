package com.devroulette.restapi.common.entity;

import com.devroulette.restapi.common.constant.ErrorMessages;
import com.mysema.commons.lang.Assert;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.slf4j.LoggerFactory;

@Getter
@MappedSuperclass
@NoArgsConstructor
public abstract class AbstractTransactableEntity extends AbstractEntity {
    private long balance;

    public AbstractTransactableEntity(long balance) {
        this.balance = balance;
    }

    public void transfer(long amount) {
        Assert.isTrue(amount > 0, ErrorMessages.NEGATIVE_AMOUNT);

        this.balance += amount;

        LoggerFactory.getLogger(this.getClass()).info(
                String.format("%1$s with ID: %2$d balance increased by: %3$d",
                        this.getClass().getSimpleName(), this.getId(), amount));
    }

    public void pay(long amount) {
        Assert.isTrue(amount > 0, ErrorMessages.NEGATIVE_AMOUNT);
        Assert.isTrue(this.balance >= amount, ErrorMessages.NOT_ENOUGH_BALANCE);

        this.balance -= amount;

        LoggerFactory.getLogger(this.getClass()).info(
                String.format("%1$s with ID: %2$d balance decreased by: %3$d",
                        this.getClass().getSimpleName(), this.getId(), amount));
    }
}
