package com.devroulette.restapi.roulette.bets.service.query.impl;

import com.devroulette.restapi.common.service.query.AbstractQueryService;
import com.devroulette.restapi.roulette.bets.entity.QUserBet;
import com.devroulette.restapi.roulette.bets.entity.UserBet;
import com.devroulette.restapi.roulette.bets.service.query.UserBetQueryService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserBetQueryServiceImpl extends AbstractQueryService<UserBet> implements UserBetQueryService {
    private static final QUserBet BET = QUserBet.userBet;

    public UserBetQueryServiceImpl(EntityManager em) {
        super(UserBet.class, em);
    }

    @Override
    public List<UserBet> getAllNotProcessedBets() {
        return this.getQueryFactory()
                .select(BET)
                .from(BET)
                .where(BET.roll.isNull())
                .stream().toList();
    }
}
