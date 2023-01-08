package com.devroulette.restapi.service.query.impl;

import com.devroulette.restapi.entity.Bet;
import com.devroulette.restapi.entity.QBet;
import com.devroulette.restapi.service.query.AbstractQueryService;
import com.devroulette.restapi.service.query.BetQueryService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BetQueryServiceImpl extends AbstractQueryService<Bet> implements BetQueryService {
    private static final QBet BET = QBet.bet;

    public BetQueryServiceImpl(EntityManager em) {
        super(Bet.class, em);
    }

    @Override
    public List<Bet> getAllNotProcessedBets() {
        return this.getQueryFactory()
                .select(BET)
                .from(BET)
                .where(BET.roll.isNull())
                .stream()
                .collect(Collectors.toList());
    }
}
