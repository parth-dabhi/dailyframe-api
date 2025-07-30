package com.todoapp.todo_api.jwt;

import com.todoapp.todo_api.service.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    // This filter will intercept requests to check for JWT tokens
    // and validate them before allowing access to protected resources.

    private final JwtTokenService tokenService;
    private final CustomUserDetailsService customUserDetailsService;

    public JwtAuthenticationFilter(JwtTokenService tokenService, CustomUserDetailsService customUserDetailsService) {
        this.tokenService = tokenService;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // : Implement JWT validation logic here, First extract the JWT token from the request header
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            username = tokenService.extractUsername(token);
        }

        // Validate Token
        // Set to Security Context

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // If the token is valid, set the authentication in the security context
            // Fetch user by username
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
            if (tokenService.validateToken(username, token, userDetails)) {
                // If the token is valid, set the authentication in the security context
                var authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        // Continue the filter chain
        filterChain.doFilter(request, response);
    }
}
