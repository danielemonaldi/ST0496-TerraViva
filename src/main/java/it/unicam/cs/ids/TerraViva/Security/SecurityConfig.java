package it.unicam.cs.ids.TerraViva.Security;

import it.unicam.cs.ids.TerraViva.Models.Role;
import it.unicam.cs.ids.TerraViva.Security.Authentication.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
                .requestMatchers(HttpMethod.POST, "/register").permitAll()
                .requestMatchers(HttpMethod.POST, "/login").permitAll()

                .requestMatchers(HttpMethod.POST, "/contest").hasAnyAuthority(
                        Role.ENTERTAINER.getAuthority(),
                        Role.AUTHORIZED_ENTERTAINER.getAuthority())
                .requestMatchers(HttpMethod.DELETE, "/contest").hasAnyAuthority(
                        Role.ENTERTAINER.getAuthority(),
                        Role.AUTHORIZED_ENTERTAINER.getAuthority())
                .requestMatchers(HttpMethod.GET, "/contest").permitAll()
                .requestMatchers(HttpMethod.GET, "/contest/{id}").permitAll()

                .requestMatchers(HttpMethod.POST, "/poi-content").hasAuthority(Role.AUTHORIZED_TOURIST.getAuthority())
                .requestMatchers(HttpMethod.POST, "/contest-content").hasAuthority(Role.AUTHORIZED_TOURIST.getAuthority())
                .requestMatchers(HttpMethod.POST, "/content/publish").hasAnyAuthority(
                        Role.CURATOR.getAuthority(),
                        Role.MANAGER.getAuthority())
                .requestMatchers("/content/{id}").permitAll()

                .requestMatchers(HttpMethod.POST, "/POI").hasAnyAuthority(
                        Role.CONTRIBUTOR.getAuthority(),
                        Role.AUTHORIZED_CONTRIBUTOR.getAuthority(),
                        Role.ENTERTAINER.getAuthority(),
                        Role.AUTHORIZED_ENTERTAINER.getAuthority(),
                        Role.AUTHORIZED_TOURIST.getAuthority())
                .requestMatchers(HttpMethod.GET, "/POI").permitAll()
                .requestMatchers(HttpMethod.GET, "/POI/{id}").permitAll()

                .requestMatchers(HttpMethod.POST, "/route").hasAnyAuthority(
                        Role.CONTRIBUTOR.getAuthority(),
                        Role.AUTHORIZED_CONTRIBUTOR.getAuthority(),
                        Role.ENTERTAINER.getAuthority(),
                        Role.AUTHORIZED_ENTERTAINER.getAuthority())
                .requestMatchers(HttpMethod.GET, "/route").permitAll()
                .anyRequest().authenticated()
            .and()
            .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}