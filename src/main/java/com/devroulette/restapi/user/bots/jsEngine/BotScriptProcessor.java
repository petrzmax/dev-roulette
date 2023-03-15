package com.devroulette.restapi.user.bots.jsEngine;

import com.devroulette.restapi.common.constant.BetType;
import com.devroulette.restapi.roulette.bets.service.BetService;
import com.devroulette.restapi.user.bots.entity.Bot;
import com.devroulette.restapi.user.bots.repository.BotRepository;
import lombok.RequiredArgsConstructor;
import org.graalvm.polyglot.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BotScriptProcessor {
    private final BotRepository botRepository;
    private final BetService betService;
    private final BotScriptCompiler scriptCompiler = new BotScriptCompiler();
    private final JavaScriptExecutor scriptExecutor = new JavaScriptExecutor();

    // TODO Multithreading, CompletableFuture
    // https://www.geeksforgeeks.org/multithreading-in-java/
    public void processBots() {
        List<Bot> botsToProcess = this.botRepository.findAllByEnabledIsTrue();
        List<Bot> botsToUpdate = new ArrayList<>();

        botsToProcess.forEach(bot -> {
            String preparedBotScript = this.scriptCompiler.compile(bot);
            Value value = this.scriptExecutor.execute(preparedBotScript);
            value.getArraySize();

            // TODO Handle many array elements, exceptions & refactor
            // Empty array as bet skip,

            Value test = value.getArrayElement(0);
            long amount = test.getMember("amount").asLong();
            BetType betType = BetType.valueOf(test.getMember("betType").asString());

            // TODO Handle it in smarter way
            try {
                this.betService.bet(bot, amount, betType);
            } catch (IllegalArgumentException e) {
                bot.setEnabled(false);
                bot.setErrorMessage(e.getMessage());
                botsToUpdate.add(bot);
            }
        });

        this.botRepository.saveAll(botsToUpdate);
    }
}
