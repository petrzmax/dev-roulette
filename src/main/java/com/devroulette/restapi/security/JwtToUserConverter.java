package com.devroulette.restapi.security;

import com.devroulette.restapi.entity.User;
import com.devroulette.restapi.factory.UserFactory;
import com.devroulette.restapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtToUserConverter implements Converter<Jwt, UsernamePasswordAuthenticationToken> {

    private final UserRepository userRepository;
    private final UserFactory userFactory;

    @Override
    public UsernamePasswordAuthenticationToken convert(Jwt jwt) {

        String email = jwt.getClaims().get("email").toString();

        Optional<User> optionalUser = this.userRepository.findByUsername(email);
        User user = optionalUser.isPresent() ? optionalUser.get() : this.userFactory.createNewUser(email);

        // TODO set authorities basing on user role?
        return new UsernamePasswordAuthenticationToken(user, jwt, Collections.EMPTY_LIST);
    }
}
