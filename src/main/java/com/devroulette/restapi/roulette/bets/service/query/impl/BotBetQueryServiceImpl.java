package com.devroulette.restapi.roulette.bets.service.query.impl;

import com.devroulette.restapi.common.service.query.AbstractQueryService;
import com.devroulette.restapi.roulette.bets.entity.BotBet;
import com.devroulette.restapi.roulette.bets.entity.QBotBet;
import com.devroulette.restapi.roulette.bets.service.query.BotBetQueryService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BotBetQueryServiceImpl extends AbstractQueryService<BotBet> implements BotBetQueryService {
    private static final QBotBet BOT_BET = QBotBet.botBet;

    public BotBetQueryServiceImpl(EntityManager em) {
        super(BotBet.class, em);
    }

    // TODO optimize, get only needed data?
    @Override
    public List<BotBet> getBotBetHistory(long botId, int limit) {
        return this.getQueryFactory()
                .select(this.BOT_BET)
                .from(this.BOT_BET)
                .where(this.BOT_BET.bot.id.eq(botId)
                        .and(this.BOT_BET.roll.isNotNull()))
                .orderBy(this.BOT_BET.id.desc())
                .limit(limit)
                .fetch();
    }
}
