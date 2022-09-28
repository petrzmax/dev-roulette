package com.devroulette.restapi.repository.impl;

import com.devroulette.restapi.entity.QRoll;
import com.devroulette.restapi.entity.Roll;
import com.devroulette.restapi.repository.AbstractBaseRepository;
import com.devroulette.restapi.repository.RollRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RollRepositoryImpl extends AbstractBaseRepository<Roll, Long> implements RollRepository {
    private static final QRoll ROLL = QRoll.roll;

    public RollRepositoryImpl(EntityManager em) {
        super(Roll.class, em);
    }

    @Override
    public List<Integer> getLast10RollsResult() {
        return this.jpaQueryFactory
                .select(this.ROLL.result)
                .from(this.ROLL)
                .orderBy(this.ROLL.id.desc())
                .limit(10)
                .stream()
                .collect(Collectors.collectingAndThen(Collectors.toList(), result -> {
                    Collections.reverse(result);
                    return result;
                }));
    }
}
