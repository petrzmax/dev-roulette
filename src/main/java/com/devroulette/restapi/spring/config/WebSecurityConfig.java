package com.devroulette.restapi.spring.config;

import com.devroulette.restapi.common.constant.Endpoints;
import com.devroulette.restapi.spring.security.CORSCustomizer;
import com.devroulette.restapi.spring.security.JwtToUserConverter;
import com.devroulette.restapi.user.constant.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final CORSCustomizer corsCustomizer;
    private final JwtToUserConverter jwtToUserConverter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        this.corsCustomizer.corsCustomizer(http);

        // TODO rewrite to new approach, as this one is deprecated and marked for removal
        //  https://stackoverflow.com/questions/43794721/spring-boot-h2-console-throws-403-with-spring-security-1-5-2
        return http.csrf().ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**"))
                .and()
                .headers(headers -> headers.frameOptions().sameOrigin())
                .authorizeRequests()
                .requestMatchers(Endpoints.ROULETTE, Endpoints.EVENTS + "/**", "/h2-console/**").permitAll()
                .requestMatchers(Endpoints.ADMIN + "/**").hasAuthority(Role.ADMIN)
                .anyRequest().authenticated()
                .and()
                .oauth2ResourceServer((oauth2) -> oauth2.jwt((jwt) -> jwt.jwtAuthenticationConverter(this.jwtToUserConverter)))
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling((exceptions) -> exceptions
                        .authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint())
                        .accessDeniedHandler(new BearerTokenAccessDeniedHandler()))
                .build();
    }
}
