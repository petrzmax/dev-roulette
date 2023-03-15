package com.devroulette.restapi.roulette.bets.entity;

import com.devroulette.restapi.common.constant.BetType;
import com.devroulette.restapi.common.constant.ErrorMessages;
import com.devroulette.restapi.common.entity.AbstractBetEntity;
import com.devroulette.restapi.user.bots.entity.Bot;
import com.mysema.commons.lang.Assert;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "BOT_BETS")
public class BotBet extends AbstractBetEntity {
    @ManyToOne
    @JoinColumn(name = "bot_id")
    private Bot bot;

    public BotBet(BetType betType, long amount, Bot bot) {
        super(betType, amount);
        Assert.isTrue(bot != null, ErrorMessages.FIELD_IS_NULL);

        this.bot = bot;
    }
}
