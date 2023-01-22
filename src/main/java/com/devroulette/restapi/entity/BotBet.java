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
@Table(name = "BOT_BETS")
public class BotBet extends AbstractBettableEntity {
    @OneToOne
    @JoinColumn(name = "bot_id")
    private Bot bot;

    public BotBet(BetType betType, long amount, Bot bot) {
        super(betType, amount);
        Assert.isTrue(bot != null, ErrorMessages.FIELD_IS_NULL);

        this.bot = bot;
    }
}
