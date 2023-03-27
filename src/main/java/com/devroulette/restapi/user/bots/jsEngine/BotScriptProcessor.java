package com.devroulette.restapi.user.bots.jsEngine;

import com.devroulette.restapi.roulette.bets.enums.BetType;
import com.devroulette.restapi.roulette.bets.service.BetService;
import com.devroulette.restapi.user.bots.entity.Bot;
import com.devroulette.restapi.user.bots.enums.BotStatus;
import com.devroulette.restapi.user.bots.repository.BotRepository;
import lombok.RequiredArgsConstructor;
import org.graalvm.polyglot.PolyglotException;
import org.graalvm.polyglot.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BotScriptProcessor {
    private final BotRepository botRepository;
    private final BetService betService;
    private final BotScriptAssembler scriptCompiler;
    private final JavaScriptExecutor scriptExecutor;

    // TODO Multithreading, CompletableFuture
    // https://www.geeksforgeeks.org/multithreading-in-java/
    public void processBots() {
        List<Bot> botsToProcess = this.botRepository.findAllByStatus(BotStatus.RUNNING);
        List<Bot> botsToUpdate = new ArrayList<>();

        botsToProcess.forEach(bot -> {
            String preparedBotScript = this.scriptCompiler.assemble(bot);
            // TODO Handle it in smarter way
            try {
                Value value = this.scriptExecutor.execute(preparedBotScript);
                value.getArraySize();

                // TODO Handle many array elements, exceptions & refactor
                // Empty array as bet skip,

                Value test = value.getArrayElement(0);
                long amount = test.getMember("amount").asLong();
                BetType betType = BetType.valueOf(test.getMember("betType").asString());


                this.betService.bet(bot, amount, betType);
            } catch (IllegalArgumentException | PolyglotException e) {
                bot.setStatus(BotStatus.FAILED);
                bot.setErrorMessage(e.getMessage());
                botsToUpdate.add(bot);
            }
        });

        this.botRepository.saveAll(botsToUpdate);
    }
}
