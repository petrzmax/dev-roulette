package com.devroulette.restapi.service.query.impl;

import com.devroulette.restapi.dto.BotDto;
import com.devroulette.restapi.entity.Bot;
import com.devroulette.restapi.entity.QBot;
import com.devroulette.restapi.service.query.AbstractQueryService;
import com.devroulette.restapi.service.query.BotQueryService;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BotQueryServiceImpl extends AbstractQueryService<Bot> implements BotQueryService {
    private static final QBot BOT = QBot.bot;

    public BotQueryServiceImpl(EntityManager em) {
        super(Bot.class, em);
    }

    @Override
    public List<BotDto> getUserBots(long userId) {
        return this.getQueryFactory()
                .from(BOT)
                .where(BOT.user.id.eq(userId))
                .select(this.getBotDtoProjection())
                .orderBy(BOT.id.asc())
                .stream().toList();
    }

    private ConstructorExpression<BotDto> getBotDtoProjection() {
        return Projections.constructor(BotDto.class,
                BOT.id, BOT.name, BOT.balance, BOT.enabled, BOT.errorMessage);
    }
}
