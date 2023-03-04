package com.devroulette.restapi.user.service;

import com.devroulette.restapi.common.constant.ErrorMessages;
import com.devroulette.restapi.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticatedUserService {

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
}
