package com.cegeka.switchfully.security;

import com.cegeka.switchfully.security.external.authentication.ExternalAuthenticaton;
import com.cegeka.switchfully.security.external.authentication.FakeAuthenticationService;
import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

public class CustomAuthenticationProviderTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    private FakeAuthenticationService fakeAuthenticationService;

    @InjectMocks
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Test
    public void authenticate_findsUserAndPassword() {
        ExternalAuthenticaton authentication = new ExternalAuthenticaton()
                .withUsername("ZWANETTA")
                .withPassword("WORST")
                .withRoles(newArrayList("CIVILIAN"));

        when(fakeAuthenticationService.getUser("ZWANETTA", "WORST"))
                .thenReturn(authentication);

        assertThat(customAuthenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(authentication.getUsername(), authentication.getPassword().toString())))
                .isEqualTo(new UsernamePasswordAuthenticationToken(authentication.getUsername(), authentication.getPassword().toString()));
    }

    @Test
    public void authenticate_doesNotFindsUserAndPassword_authenticationProvider() {
        expectedException.expect(AuthenticationException.class);

//        customAuthenticationProvider.authenticate();
    }

    @Test
    public void supportsUsernamePasswordAuthenticationToken_true() {
    }

    public void doesNotSupportsUsernamePasswordAuthenticationToken_false() {
    }
}