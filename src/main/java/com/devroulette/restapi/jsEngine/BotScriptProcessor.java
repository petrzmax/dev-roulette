package com.devroulette.restapi.jsEngine;

import com.devroulette.restapi.constant.BetType;
import com.devroulette.restapi.entity.Bot;
import com.devroulette.restapi.repository.BotRepository;
import com.devroulette.restapi.service.BetService;
import lombok.RequiredArgsConstructor;
import org.graalvm.polyglot.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BotScriptProcessor {
    private final BotRepository botRepository;
    private final BetService betService;
    private final BotScriptCompiler scriptBuilder = new BotScriptCompiler();
    private final JavaScriptExecutor scriptExecutor = new JavaScriptExecutor();

    public void processBots() {
        Iterable<Bot> botsToProcess = this.botRepository.findAllByEnabledIsTrue();

        botsToProcess.forEach(bot -> {
            String preparedBotScript = this.scriptBuilder.compile(bot);
            Value value = this.scriptExecutor.execute(preparedBotScript);
            value.getArraySize();

            // TODO Handle many array elements, exceptions & refactor

            Value test = value.getArrayElement(0);
            long amount = test.getMember("amount").asLong();
            BetType betType = BetType.valueOf(test.getMember("betType").asString());
            this.betService.bet(bot, amount, betType);
        });
    }
}
