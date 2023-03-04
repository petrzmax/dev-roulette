package com.devroulette.restapi.bots.jsEngine;

import com.devroulette.restapi.bots.entity.Bot;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BotScriptCompiler {

    // TODO add getColor(roll), isEven(), isOdd()
    // TODO add getRollHistory()
    // TODO Move to some other class
    private final static String MAIN_SCRIPT_TEMPLATE = """
            function getBalance() {
                return %1$d;
            }
                        
            function getLast10Rolls() {
                return %2$s;
            }
                        
            function getMyBets() {
                return %3$s;
            }
                        
            function executeBotLogic() {
                %4$s
            }
                        
            executeBotLogic();
            """;

    // TODO use real rolls & bets
    public String compile(Bot bot) {
        String last10Rolls = "[1, 15, 24, 30, 0, 12, 16, 2, 2, 10]";

        String compiledScript = String.format(MAIN_SCRIPT_TEMPLATE,
                bot.getBalance(), last10Rolls, "myBets", bot.getScriptBody());

        return compiledScript;
    }
}
