package com.devroulette.restapi.user.bots.jsEngine;

import com.devroulette.restapi.roulette.bets.service.BetService;
import com.devroulette.restapi.user.bots.entity.Bot;
import com.devroulette.restapi.user.bots.enums.BotStatus;
import com.devroulette.restapi.user.bots.repository.BotRepository;
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
class BotScriptProcessorTest {

    @InjectMocks
    BotScriptProcessor scriptProcessor;

    @Mock
    BotRepository botRepository;

    @Mock
    BetService betService;

    // TODO WIP
    @Test
    @Disabled
    void processBots() {
        // given
        Bot bot = new Bot();
        String script = """
                const a = 10;
                const b = 15;
                                
                return [{
                    amount: a + b,
                    betType: "EVEN"
                }];
                """;

        bot.setScriptBody(script);

        List<Bot> bots = List.of(bot);
        when(this.botRepository.findAllByStatus(BotStatus.RUNNING)).thenReturn(bots);

        // when
        this.scriptProcessor.processBots();

        //then
        Assertions.assertThat(true).isTrue();
    }
}