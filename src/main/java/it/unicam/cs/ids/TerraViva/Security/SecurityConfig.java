package it.unicam.cs.ids.TerraViva.Security;

import it.unicam.cs.ids.TerraViva.Models.Role;
import it.unicam.cs.ids.TerraViva.Security.Authentication.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private AuthenticationFilter authFilter;
    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
            .authorizeRequests()
                .requestMatchers("/register", "/login").permitAll()
                .requestMatchers("/creation/contest", "/deletion/contest").hasAnyAuthority(
                        Role.CONTRIBUTOR.getAuthority(),
                        Role.AUTHORIZED_CONTRIBUTOR.getAuthority())
                .requestMatchers("/creation/poi-content",  "/creation/contest-content").hasAuthority(Role.AUTHORIZED_TOURIST.getAuthority())
                .requestMatchers("/creation/POI").hasAnyAuthority(
                        Role.CONTRIBUTOR.getAuthority(),
                        Role.AUTHORIZED_CONTRIBUTOR.getAuthority(),
                        Role.ENTERTAINER.getAuthority(),
                        Role.AUTHORIZED_ENTERTAINER.getAuthority())
                .anyRequest().authenticated()
            .and()
            .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}