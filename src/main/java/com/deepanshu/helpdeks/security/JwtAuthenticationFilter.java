package com.deepanshu.helpdeks.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        String jwt = null;
        String username = null;

        // 1. Check if the header has a Bearer token

        if(authHeader != null && authHeader.startsWith("Bearer")){
            jwt = authHeader.substring(7);

            try {
                username = jwtUtil.extractUserName(jwt);
            }catch (Exception e){
                System.out.println("JWT Extraction Failed: " + e.getMessage());
            }
        }

        // 2. Validate token and set authentication context

        if(username != null && SecurityContextHolder.getContext().getAuthentication() ==null){

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    // Empty authorities list for now
                    username, null , new ArrayList<>()
            );

            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            // Mark the user as authenticated in Spring Security context
            SecurityContextHolder.getContext().setAuthentication(authToken);

        }

        // 3. Continue the request chain
        filterChain.doFilter(request, response);

    }
}
