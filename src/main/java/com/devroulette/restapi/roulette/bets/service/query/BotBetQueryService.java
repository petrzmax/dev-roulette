package com.devroulette.restapi.roulette.bets.service.query;

import com.devroulette.restapi.roulette.bets.entity.BotBet;

import java.util.List;

public interface BotBetQueryService {
    /**
     * Gets last BotBets in descending by id order.
     *
     * @param limit results amount
     * @return list of last BotBets
     */
    List<BotBet> getBotBetHistory(long botId, int limit);
}
