package com.joaquin.ClinicaMVC.configuration;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{
    
    private final JwtService jwtService;
    private final UserDetailsService UserDetailsService;

    @Override
    protected void doFilterInternal(
        @NonNull HttpServletRequest request, 
        @NonNull HttpServletResponse response, 
        @NonNull FilterChain filterChain)
        throws ServletException, IOException {


        final String autHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        //checks if the header is not null and if it doesnt start with the word "Bearer"
        if (autHeader == null || !autHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }

        //set jwt as the String following the "Bearer" word
        jwt = autHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt);

        //Checks if the userEmail is not null and if the user has not been authenticated
       if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){

        UserDetails userDetails = this.UserDetailsService.loadUserByUsername(userEmail);

        // if the token is valid the SecurityContexHolder is actualizated
        if(jwtService.isTokenValid(jwt, userDetails)){
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities());

            authenticationToken.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
            );

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
        
       }

    }

}
