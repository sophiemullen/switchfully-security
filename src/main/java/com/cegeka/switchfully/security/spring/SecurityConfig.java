package com.cegeka.switchfully.security.spring;

import com.cegeka.switchfully.security.rest.ArmyResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;

import static com.cegeka.switchfully.security.rest.ArmyResource.ARMY_RESOURCE_PATH;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
//annotation needed for spring to pick up the @PreAuthorize annotations on the ArmyResource
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationEntryPoint authEntryPoint;
    @Autowired
    private ArmyAuthenticationProvider armyAuthenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.csrf().disable().authorizeRequests()
//                example .antMatchers(GET, "/my/rest/api/{somePathParam}").access("@antiHackerService.stopHackers(#somePathParam)");
                .antMatchers(ARMY_RESOURCE_PATH + "/promote/{promotee}").access("@criminalRecordService.hasCriminalRecord(#promotee)")
//                .antMatchers("/army/discharge/**").hasRole("HUMAN_RELATIONSHIPS")
//                .antMatchers("/army/nuke").hasRole("GENERAL")
//                .antMatchers("/army/**").hasAnyRole("PRIVATE", "GENERAL")
                .and().httpBasic()
                .authenticationEntryPoint(authEntryPoint);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(armyAuthenticationProvider);
//        auth.inMemoryAuthentication()
//                .withUser("ZWANETTA").password("WORST").roles("CIVILIAN")
//                .and()
//                .withUser("JMILLER").password("THANKS").roles("PRIVATE")
//                .and()
//                .withUser("UNCLE").password("SAM").roles("HUMAN_RELATIONSHIPS")
//                .and()
//                .withUser("GENNY").password("RALLY").roles("GENERAL");
    }

}
