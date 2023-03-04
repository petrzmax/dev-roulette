package com.devroulette.restapi.roulette.factory;

import com.devroulette.restapi.common.RouletteUtils;
import com.devroulette.restapi.roulette.entity.Roll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RollFactory {
    private static final Logger LOG = LoggerFactory.getLogger(RollFactory.class);

    public Roll createRandomRoll() {
        long currentTimeSeed = System.currentTimeMillis();
        Random generator = new Random(currentTimeSeed);

        int rollResult = generator.nextInt(0, RouletteUtils.ROULETTE_NUMBER_SEQUENCE.size());
        float tileCoverageFactor = generator.nextFloat();

        LOG.info(String.format("Rolled: %1$d, tileCoverage: %2$f", rollResult, tileCoverageFactor));

        // TODO make it use real seed
        return new Roll(String.valueOf(currentTimeSeed), rollResult, tileCoverageFactor);
    }
}
