package com.devroulette.restapi.service.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.Getter;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public abstract class AbstractQueryService<T> extends SimpleJpaRepository<T, Long> {

    @Getter
    private final JPAQueryFactory queryFactory;
    private final EntityManager entityManager;

    public AbstractQueryService(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.entityManager = em;
        this.queryFactory = new JPAQueryFactory(this.entityManager);
    }
}
