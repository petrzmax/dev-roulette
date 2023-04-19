package com.devroulette.restapi.user.bots.jsEngine;

import com.devroulette.restapi.roulette.bets.entity.BotBet;
import com.devroulette.restapi.roulette.bets.enums.BetType;
import com.devroulette.restapi.roulette.bets.service.query.BotBetQueryService;
import com.devroulette.restapi.roulette.entity.Roll;
import com.devroulette.restapi.roulette.service.query.RollQueryService;
import com.devroulette.restapi.user.bots.entity.Bot;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BotScriptAssemblerTest {

    private static final List<Integer> ROLL_10_HISTORY = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    @InjectMocks
    BotScriptAssembler scriptCompiler;

    @Mock
    RollQueryService rollQueryService;
    @Mock
    BotBetQueryService betQueryService;
//    @Spy
//    BotBetMapper betMapper = new BotBetMapperImpl();

    // TODO WIP
    @Test
    @Disabled
    void build() {
        // given
        Bot bot = new Bot();
        bot.transfer(5000);
        bot.setScriptBody("Fancy, smart and happy script");


        BotBet betOne = new BotBet(BetType.EVEN, 100, bot);
        BotBet betTwo = new BotBet(BetType.EVEN, 100, bot);
        betOne.setRoll(new Roll("farmazon", 2, 0.5f));
        betTwo.setRoll(new Roll("farmazon Dwa", 3, 0.4f));

        when(this.rollQueryService.getRollHistory(10)).thenReturn(ROLL_10_HISTORY);
        when(this.betQueryService.getBotBetHistory(bot.getId(), 10)).thenReturn(List.of(betOne, betTwo));

        // when
        String result = this.scriptCompiler.assemble(bot);

        // then
        Assertions.assertThat(result).isEqualTo("test");
    }
}