package com.devroulette.restapi.repository;

import com.devroulette.restapi.entity.Roll;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

@NoRepositoryBean
public interface RollRepository extends PagingAndSortingRepository<Roll, Long> {
    /**
     * Gets last 10 results in correct order for FE.
     *
     * @return list of last 10 rolls result
     */
    List<Integer> getLast10RollsResult();
}
