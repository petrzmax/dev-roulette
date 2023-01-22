package com.devroulette.restapi.jsEngine;

import com.devroulette.restapi.entity.Bot;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BotScriptCompilerTest {

    @InjectMocks
    BotScriptCompiler scriptBuilder;

    // TODO WIP
    @Test
    void build() {
        // given
        Bot bot = new Bot();
        bot.transfer(5000);
        bot.setScriptBody("Fancy, smart and happy script");

        // when
        String result = this.scriptBuilder.compile(bot);

        // then
        Assertions.assertThat(result).isEqualTo("test");
    }
}