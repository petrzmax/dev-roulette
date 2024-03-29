package com.devroulette.restapi.roulette.service.query;

import com.devroulette.restapi.history.dto.RollDto;
import com.devroulette.restapi.roulette.entity.Roll;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RollQueryService extends JpaRepository<Roll, Long> {
    /**
     * Gets last results in descending by id order.
     *
     * @param limit results amount
     * @return list of last roll results
     */
    List<Integer> getRollHistory(int limit);

    /**
     * Returns tile coverage of a last roll
     *
     * @return last roll tile coverage
     */
    Float getLastRollTileCoverage();

    /**
     * Gets paginated Roll History
     *
     * @param pageable contains pagination information
     * @return Page of RollDto
     */
    Page<RollDto> getPaginatedRollHistory(Pageable pageable);
}
