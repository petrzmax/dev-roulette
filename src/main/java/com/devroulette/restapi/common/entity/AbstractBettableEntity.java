package com.devroulette.restapi.common.entity;

import com.devroulette.restapi.common.constant.BetType;
import com.devroulette.restapi.common.constant.ErrorMessages;
import com.devroulette.restapi.roulette.entity.Roll;
import com.mysema.commons.lang.Assert;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@MappedSuperclass
@NoArgsConstructor
public abstract class AbstractBettableEntity extends AbstractEntity {
    @Enumerated(EnumType.STRING) // It will consume much more db memory. Use only for testing
    private BetType betType;
    private long amount;

    @Setter
    @OneToOne
    @JoinColumn(name = "roll_id")
    private Roll roll;

    public AbstractBettableEntity(BetType betType, long amount) {
        Assert.isTrue(betType != null, ErrorMessages.FIELD_IS_NULL);
        Assert.isTrue(amount > 0, ErrorMessages.NEGATIVE_AMOUNT);

        this.betType = betType;
        this.amount = amount;
    }

    public boolean isVictory() {
        if (this.roll != null) {
            return this.betType.equals(this.roll.getColor()) || this.betType.equals(this.roll.getParity());
        }
        throw new IllegalStateException(ErrorMessages.ROLL_NOT_ASSIGNED);
    }

    public long getPrize() {
        return this.betType.getPrize(this.amount);
    }
}
