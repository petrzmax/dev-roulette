package com.devroulette.restapi.entity;

import com.devroulette.restapi.constant.BetType;
import com.devroulette.restapi.constant.ErrorMessages;
import com.mysema.commons.lang.Assert;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "BETS")
public class Bet extends AbstractEntity {
    @NonNull
    @Enumerated(EnumType.STRING) // It will consume much more db memory. Use only for testing
    private BetType betType;
    private long amount;

    @NonNull
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "bot_id")
    private Bot bot;

    @Setter
    @OneToOne
    @JoinColumn(name = "roll_id")
    private Roll roll;

    public Bet(BetType betType, long amount, User user) {
        Assert.isTrue(betType != null, ErrorMessages.FIELD_IS_NULL);
        Assert.isTrue(amount > 0, ErrorMessages.NEGATIVE_AMOUNT);
        Assert.isTrue(user != null, ErrorMessages.FIELD_IS_NULL);

        this.betType = betType;
        this.amount = amount;
        this.user = user;
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
