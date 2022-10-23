package com.devroulette.restapi.service;

import com.devroulette.restapi.dto.RollDto;
import com.devroulette.restapi.service.query.RollQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RollService {
    private final RollQueryService rollQueryService;

    public Page<RollDto> getRollHistoryWithPagination(Pageable pageable) {
        return this.rollQueryService.getPaginatedRollHistory(pageable);
    }
}
