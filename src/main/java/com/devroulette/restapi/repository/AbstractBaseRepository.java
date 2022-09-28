package com.devroulette.restapi.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;

@NoRepositoryBean
public abstract class AbstractBaseRepository<T, ID> extends SimpleJpaRepository<T, ID> {

    protected final JPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;

    public AbstractBaseRepository(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.entityManager = em;
        this.jpaQueryFactory = new JPAQueryFactory(this.entityManager);
    }

    public T findByIdMandatory(ID id) throws IllegalArgumentException {
        return this.findById(id).orElseThrow(() -> new IllegalArgumentException("Entity not found with id " + id));
    }
}
