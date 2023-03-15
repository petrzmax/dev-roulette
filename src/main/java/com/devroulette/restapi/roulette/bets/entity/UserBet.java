package com.devroulette.restapi.roulette.bets.entity;

import com.devroulette.restapi.common.constant.ErrorMessages;
import com.devroulette.restapi.common.entity.AbstractBetEntity;
import com.devroulette.restapi.roulette.bets.enums.BetType;
import com.devroulette.restapi.user.entity.User;
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
public class UserBet extends AbstractBetEntity {
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public UserBet(BetType betType, long amount, User user) {
        super(betType, amount);
        Assert.isTrue(user != null, ErrorMessages.FIELD_IS_NULL);

        this.user = user;
    }
}
