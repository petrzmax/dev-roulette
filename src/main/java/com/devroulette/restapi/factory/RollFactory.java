package com.devroulette.restapi.factory;

import com.devroulette.restapi.entity.Roll;
import com.devroulette.restapi.utils.RouletteUtils;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RollFactory {
    public Roll createRandomRoll() {
        long currentTimeSeed = System.currentTimeMillis();
        Random generator = new Random(currentTimeSeed);

        int rollResult = generator.nextInt(0, RouletteUtils.ROULETTE_NUMBER_SEQUENCE.size());
        float tileCoverageFactor = generator.nextFloat();

        // TODO logging?
        System.out.println("Rolled: " + rollResult + " tileCoverage: " + tileCoverageFactor);

        // TODO make it use real seed
        return new Roll(String.valueOf(currentTimeSeed), rollResult, tileCoverageFactor);
    }
}
