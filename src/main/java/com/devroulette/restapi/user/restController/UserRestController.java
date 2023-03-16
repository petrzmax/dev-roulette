package com.devroulette.restapi.user.restController;

import com.devroulette.restapi.common.constant.Endpoints;
import com.devroulette.restapi.user.dto.UserDataDto;
import com.devroulette.restapi.user.service.AuthenticatedUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Endpoints.USER)
@RequiredArgsConstructor
public class UserRestController {
    private final AuthenticatedUserService authenticatedUserService;

    @GetMapping()
    public ResponseEntity getUserData() {
        UserDataDto userDataDto = this.authenticatedUserService.getUserDataDto();
        return new ResponseEntity(userDataDto, HttpStatus.OK);
    }
}
