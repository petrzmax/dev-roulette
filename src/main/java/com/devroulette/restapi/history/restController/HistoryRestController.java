package com.devroulette.restapi.history.restController;

import com.devroulette.restapi.roulette.service.RollService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/roll")
@RequiredArgsConstructor
public class HistoryRestController {
    private final RollService rollService;

    @GetMapping("history/{page}/{pageSize}")
    public ResponseEntity getRollHistory(@PathVariable Integer page, @PathVariable Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return new ResponseEntity(this.rollService.getRollHistoryWithPagination(pageable), HttpStatus.OK);
    }
}
