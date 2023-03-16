package com.devroulette.restapi.user.service;

import com.devroulette.restapi.common.constant.ErrorMessages;
import com.devroulette.restapi.user.bots.dto.BotDto;
import com.devroulette.restapi.user.bots.service.query.BotQueryService;
import com.devroulette.restapi.user.dto.UserDataDto;
import com.devroulette.restapi.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticatedUserService {
    private final BotQueryService botQueryService;

    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return (User) authentication.getPrincipal();
        }

        throw new AuthenticationCredentialsNotFoundException(ErrorMessages.NO_AUTHENTICATED_USER);
    }

    public long getUserId() {
        return this.getUser().getId();
    }

    // TODO Maybe one query which will return whole dto?
    public UserDataDto getUserDataDto() {
        User user = this.getUser();
        String role = user.getRole();
        long balance = user.getBalance();
        List<BotDto> bots = this.botQueryService.getUserBots(user.getId());

        return new UserDataDto(role, balance, bots);
    }
}
