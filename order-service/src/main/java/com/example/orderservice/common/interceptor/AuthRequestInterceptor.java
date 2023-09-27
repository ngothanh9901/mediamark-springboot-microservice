package com.example.orderservice.common.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class AuthRequestInterceptor implements RequestInterceptor {
  @Override
  public void apply(RequestTemplate template) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null
        || authentication instanceof AnonymousAuthenticationToken                // SKC : anonymous 인 경우에 빈값으로 리턴해야 함.
        || !authentication.isAuthenticated()) {
      return;
    }
    Jwt jwt = (Jwt) authentication.getPrincipal();
    template.header("Authorization","Bearer "+jwt.getTokenValue());
  }
}
