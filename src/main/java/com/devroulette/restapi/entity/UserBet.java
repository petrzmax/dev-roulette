package com.devroulette.restapi.entity;

import com.devroulette.restapi.constant.BetType;
import com.devroulette.restapi.constant.ErrorMessages;
import com.mysema.commons.lang.Assert;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "USER_BETS")
public class UserBet extends AbstractBettableEntity {
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public UserBet(BetType betType, long amount, User user) {
        super(betType, amount);
        Assert.isTrue(user != null, ErrorMessages.FIELD_IS_NULL);

        this.user = user;
    }
}
