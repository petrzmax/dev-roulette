package com.devroulette.restapi.service;

import com.devroulette.restapi.dto.RouletteDto;
import com.devroulette.restapi.entity.Roll;
import com.devroulette.restapi.repository.RollRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class RouletteService {

    private final RollRepository rollRepository;
    private final CacheManager cacheManager;

    private String nextRollTimeStamp;
    private float tileCoverageFactor;

    @Cacheable("getCurrentRouletteState")
    public RouletteDto getCurrentRouletteState() {
        List<Integer> rollHistory = this.rollRepository.getLast10RollsResult();

        return new RouletteDto(rollHistory, this.tileCoverageFactor, this.nextRollTimeStamp);
    }

    @Scheduled(cron = "*/3 * * * * *")
    protected void roll() {
        this.cacheManager.getCache("getCurrentRouletteState").clear();

        Long currentTimeSeed = System.currentTimeMillis();

        Random generator = new Random(currentTimeSeed);

        Integer roll = generator.nextInt(0, 30);
        this.tileCoverageFactor = generator.nextFloat();

        System.out.println("Rolled: " + roll);
        this.rollRepository.save(new Roll(currentTimeSeed.toString(), roll));
        this.nextRollTimeStamp = ZonedDateTime.now(ZoneOffset.UTC).plusSeconds(30).format(DateTimeFormatter.ISO_INSTANT);
    }
}
