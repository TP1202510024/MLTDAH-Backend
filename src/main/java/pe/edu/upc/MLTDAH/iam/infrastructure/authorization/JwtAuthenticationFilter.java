package pe.edu.upc.MLTDAH.iam.infrastructure.authorization;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import pe.edu.upc.MLTDAH.iam.application.internal.outboundservices.TokenService;
import pe.edu.upc.MLTDAH.iam.domain.model.aggregates.User;
import pe.edu.upc.MLTDAH.iam.domain.services.UserQueryService;
import pe.edu.upc.MLTDAH.iam.infrastructure.authorization.model.UserDetailsImplementation;
import pe.edu.upc.MLTDAH.iam.infrastructure.authorization.model.UsernamePasswordAuthenticationTokenBuilder;
import pe.edu.upc.MLTDAH.iam.infrastructure.authorization.services.UserDetailsServiceImplementation;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Qualifier("defaultUserDetailsService")
    private final UserDetailsService userDetailsService;
    private final TokenService tokenService;

    public JwtAuthenticationFilter(UserDetailsService userDetailsService, TokenService tokenService) {
        this.userDetailsService = userDetailsService;
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String bearerToken = request.getHeader("Authorization");

            if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
                String token = bearerToken.substring(7);

                if (tokenService.validateToken(token)) {
                    String email = tokenService.getEmailFromToken(token);
                    UserDetailsImplementation userDetails =  (UserDetailsImplementation) userDetailsService.loadUserByUsername(email);
                    SecurityContextHolder.getContext().setAuthentication(UsernamePasswordAuthenticationTokenBuilder.build(userDetails, request));
                }
            }
        } catch (Exception ex) {
            LOGGER.error("Cannot set user authentication: {}", ex.getMessage());
        }

        filterChain.doFilter(request, response);
    }
}
