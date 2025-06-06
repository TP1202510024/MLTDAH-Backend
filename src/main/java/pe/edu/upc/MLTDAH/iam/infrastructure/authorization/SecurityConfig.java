package pe.edu.upc.MLTDAH.iam.infrastructure.authorization;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pe.edu.upc.MLTDAH.iam.application.internal.outboundservices.TokenService;
import pe.edu.upc.MLTDAH.shared.infrastructure.documentation.openapi.configuration.OpenApiConfiguration;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private final UserDetailsService userDetailsService;
    private final TokenService tokenService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/v1/authentication/**",
                                "/v3/api-docs/**",
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/swagger-resources/**",
                                "/webjars/**").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(new JwtAuthenticationFilter(userDetailsService, tokenService), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public OpenAPI OpenAPi() {
        return OpenApiConfiguration.MLTDAHAPI();
    }

    public SecurityConfig(@Qualifier("defaultUserDetailsService") UserDetailsService userDetailsService, TokenService tokenService) {
        this.userDetailsService = userDetailsService;
        this.tokenService = tokenService;
    }
}