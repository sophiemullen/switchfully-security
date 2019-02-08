package com.cegeka.switchfully.security;

import com.cegeka.switchfully.security.external.authentication.FakeAuthenticationService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final FakeAuthenticationService fakeAuthenticationService;

    public CustomAuthenticationProvider(FakeAuthenticationService fakeAuthenticationService) {
        this.fakeAuthenticationService = fakeAuthenticationService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
// need to create an Army Authentification which extends from Authentification, not use token
        if (fakeAuthenticationService.getUser(authentication.getName(), authentication.getCredentials().toString()) != null) {
            return new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials().toString());
        } else return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
