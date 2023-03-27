package com.devroulette.restapi.user.bots.jsEngine;

import com.devroulette.restapi.roulette.bets.dto.BotBetDto;
import com.devroulette.restapi.roulette.bets.mapper.BotBetMapper;
import com.devroulette.restapi.roulette.bets.service.query.BotBetQueryService;
import com.devroulette.restapi.roulette.service.query.RollQueryService;
import com.devroulette.restapi.user.bots.entity.Bot;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.devroulette.restapi.roulette.service.RouletteService.ROLL_HISTORY_LENGTH;

@Component
@RequiredArgsConstructor
public class BotScriptAssembler {

    // TODO add getColor(roll), isEven(), isOdd()
    // TODO add getRollHistory()
    // TODO Move to some other class
    // TODO MyBets interface? If TypeScript is possible?
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

    private final RollQueryService rollQueryService;
    private final BotBetQueryService betQueryService;
    private final BotBetMapper botBetMapper;

    public String assemble(Bot bot) {

        String last10Rolls = this.rollQueryService.getRollHistory(ROLL_HISTORY_LENGTH).toString();
        List<BotBetDto> last10Bets = this.betQueryService.getBotBetHistory(bot.getId(), 10).stream()
                .map(this.botBetMapper::toBotBetDto)
                .toList();

        String compiledScript = String.format(MAIN_SCRIPT_TEMPLATE,
                bot.getBalance(), last10Rolls, this.toJson(last10Bets), bot.getScriptBody());

        return compiledScript;
    }

    private String toJson(List<?> list) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
