package com.example.restcontroller.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class ApiKeyAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private ApiKeyRepository apiKeyRepository;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        String apiKey = (String) authentication.getPrincipal();
        return apiKeyRepository.findByKeyAndActiveTrue(apiKey)
                .map(entity -> {
                    ApiKeyAuthentication auth = new ApiKeyAuthentication(apiKey);
                    auth.setAuthenticated(true);
                    return (Authentication) auth;
                })
                .orElseThrow(() -> new InvalidApiKeyException("Invalid API Key"));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return ApiKeyAuthentication.class.isAssignableFrom(authentication);
    }

    public static class InvalidApiKeyException extends AuthenticationException {
        public InvalidApiKeyException(String msg) { super(msg); }
    }
}