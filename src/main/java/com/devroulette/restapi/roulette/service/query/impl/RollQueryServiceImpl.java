package com.devroulette.restapi.roulette.service.query.impl;

import com.devroulette.restapi.common.service.query.AbstractQueryService;
import com.devroulette.restapi.history.dto.RollDto;
import com.devroulette.restapi.roulette.entity.QRoll;
import com.devroulette.restapi.roulette.entity.Roll;
import com.devroulette.restapi.roulette.service.query.RollQueryService;
import com.querydsl.core.types.Projections;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RollQueryServiceImpl extends AbstractQueryService<Roll> implements RollQueryService {
    private static final QRoll ROLL = QRoll.roll;

    public RollQueryServiceImpl(EntityManager em) {
        super(Roll.class, em);
    }

    // TODO Cache
    @Override
    public List<Integer> getRollHistory(int limit) {
        return this.getQueryFactory()
                .select(this.ROLL.result)
                .from(this.ROLL)
                .orderBy(this.ROLL.id.desc())
                .limit(limit)
                .fetch();
    }

    @Override
    public Float getLastRollTileCoverage() {
        return this.getQueryFactory()
                .select(ROLL.tileCoverageFactor)
                .from(ROLL)
                .orderBy(this.ROLL.id.desc())
                .fetchFirst();
    }

    @Override
    public Page<RollDto> getPaginatedRollHistory(Pageable pageable) {
        List<RollDto> result = this.getQueryFactory()
                .select(Projections.constructor(RollDto.class, ROLL.id, ROLL.seed, ROLL.result, ROLL.timestamp))
                .from(ROLL)
                .orderBy(this.ROLL.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return PageableExecutionUtils.getPage(result, pageable, () -> this.getRollCount());
    }

    private Long getRollCount() {
        return this.getQueryFactory()
                .selectFrom(ROLL)
                .fetchCount();
    }
}
