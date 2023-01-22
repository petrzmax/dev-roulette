package com.devroulette.restapi.jsEngine;

import com.devroulette.restapi.entity.Bot;
import com.devroulette.restapi.repository.BotRepository;
import com.devroulette.restapi.service.BetService;
import org.assertj.core.api.Assertions;
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
        when(this.botRepository.findAllByEnabledIsTrue()).thenReturn(bots);

        // when
        this.scriptProcessor.processBots();

        //then
        Assertions.assertThat(true).isTrue();
    }
}