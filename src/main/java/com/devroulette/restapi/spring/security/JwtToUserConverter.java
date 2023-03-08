package com.devroulette.restapi.spring.security;

import com.devroulette.restapi.user.entity.User;
import com.devroulette.restapi.user.factory.UserFactory;
import com.devroulette.restapi.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

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
        User user = optionalUser.isPresent() ? optionalUser.get() : this.userFactory.getUser(email);

        return new UsernamePasswordAuthenticationToken(user, jwt, user.getAuthorities());
    }
}
