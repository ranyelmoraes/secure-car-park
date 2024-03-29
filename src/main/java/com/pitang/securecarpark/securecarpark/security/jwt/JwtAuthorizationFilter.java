package com.pitang.securecarpark.securecarpark.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUserDetailsService detailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String token = request.getHeader(JwtUtils.JWT_AUTHORIZATION);

        if (token == null || !token.startsWith(JwtUtils.JWT_BEARER)) {
            log.info("Unauthorized");
            filterChain.doFilter(request, response);
            return;
        }

        if(JwtUtils.isValid(token)){
            log.warn("Unauthorized - invalid session");
            filterChain.doFilter(request, response);
            return;
        }

        String login = JwtUtils.getLogin(token);

        toAuthentication (request, login);

        filterChain.doFilter(request, response);
    }

    private void toAuthentication(HttpServletRequest request, String login) {
        UserDetails userDetails = detailsService.loadUserByUsername(login);

        UsernamePasswordAuthenticationToken authenticationToken = UsernamePasswordAuthenticationToken
                .authenticated(userDetails, null, userDetails.getAuthorities());

        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
}
