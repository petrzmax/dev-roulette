package com.devroulette.restapi.service.query.impl;

import com.devroulette.restapi.entity.QUserBet;
import com.devroulette.restapi.entity.UserBet;
import com.devroulette.restapi.service.query.AbstractQueryService;
import com.devroulette.restapi.service.query.UserBetQueryService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

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
                .stream()
                .collect(Collectors.toList());
    }
}
