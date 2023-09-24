package com.example.authorizationserver.security;

import com.example.authorizationserver.exception.UnauthorizedException;
import com.example.authorizationserver.model.User;
import com.example.authorizationserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;


/**
 * Provider which will validate the Authentication object present in the SecurityContext.
 * The only acceptable Authentication object is the UsernamePasswordAuthenticationToken which comes from the
 * UserAuthenticationConverter. Then, from the username and password present in the Authentication object, I
 * validate the information against the database.
 * If the username and password don't match with the data present in the database, null is returned as the
 * Authentication object in the SecurityContext.
 */
@Component
@RequiredArgsConstructor
public class UserAuthenticationProvider implements AuthenticationProvider {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailServices;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = Objects.toString(authentication.getCredentials(), null);
        if (StringUtils.isBlank(name) || StringUtils.isBlank(password)) {
            throw new UnauthorizedException("Wrong username or password")
                    .code(HttpStatus.UNAUTHORIZED.value())
                    .displayMessage("auth.wrong_credentials");
        }
        UserPrincipal userPrincipal = (UserPrincipal) userDetailServices.loadUserByUsername(name);
        if (Objects.nonNull(userPrincipal)) {
            User user = userPrincipal.getUser();
            if (user.getLoginFailedTimes() > 5) {
                throw new UnauthorizedException("Login failed too many")
                        .displayMessage("auth.login_failed_too_many");
            }
            if (passwordEncoder.matches(password, userPrincipal.getPassword())) {
                user.setLoginFailedTimes(0);
                if (!user.getLoginTimes().equals(0)) {
                    user.setLoginTimes(user.getLoginTimes() + 1);
                }
                return new UsernamePasswordAuthenticationToken(userPrincipal, password, userPrincipal.getAuthorities());
            } else {
                user.setLoginFailedTimes(user.getLoginFailedTimes() + 1);
            }
            userRepository.save(user);
        }
        throw new UnauthorizedException("Wrong password")
                .code(HttpStatus.UNAUTHORIZED.value())
                .displayMessage("auth.wrong_credentials");

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}
