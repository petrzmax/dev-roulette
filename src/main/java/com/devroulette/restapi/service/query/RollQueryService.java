package com.devroulette.restapi.service.query;

import com.devroulette.restapi.entity.Roll;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RollQueryService extends JpaRepository<Roll, Long> {
    /**
     * Gets last 10 results in order suitable for FE roll history bar.
     *
     * @return list of last 10 roll results
     */
    List<Integer> getRollHistory();

    /**
     * Returns tile coverage of a last roll
     *
     * @return last roll tile coverage
     */
    Float getLastRollTileCoverage();
}
