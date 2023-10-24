package com.ownsprojects.ecomerce.security.filters;

import com.ownsprojects.ecomerce.security.jwt.JwtUtils;
import com.ownsprojects.ecomerce.service.CustomerService;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Represents a filter of authorization.
 */
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;
    private final CustomerService customerService;

    public JwtAuthorizationFilter(JwtUtils jwtUtils, CustomerService customerService) {
        this.jwtUtils = jwtUtils;
        this.customerService = customerService;
    }

    /**
     * Override this method to provide a filter function that will be used to filter customers.
     * @param request The request object that will be used to filter customers.
     * @param response The response object that will be used to filter customers.
     * @param filterChain The filter chain that will be used to filter customers.
     * @throws ServletException The servlet exception that occurred while processing the request.
     * @throws IOException The servlet exception that occurred while processing the request.
     */
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        String tokenHeader = request.getHeader("Authorization");

        if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
            String token = tokenHeader.substring(7);

            if (jwtUtils.isTokenValid(token)) {
                String username = jwtUtils.getUsernameFromToken(token);
                UserDetails userDetails = customerService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
