package com.devroulette.restapi.factory;

import com.devroulette.restapi.entity.Roll;
import com.devroulette.restapi.utils.RouletteUtils;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RollFactory {
    public Roll createRandomRoll() {
        Long currentTimeSeed = System.currentTimeMillis();
        Random generator = new Random(currentTimeSeed);

        Integer rollResult = generator.nextInt(0, RouletteUtils.ROULETTE_NUMBER_SEQUENCE.size());
        Float tileCoverageFactor = generator.nextFloat();

        // TODO logging?
        System.out.println("Rolled: " + rollResult + " tileCoverage: " + tileCoverageFactor);

        return new Roll(currentTimeSeed.toString(), rollResult, tileCoverageFactor);
    }
}
