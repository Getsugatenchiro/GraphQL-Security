package com.graphql_security.security;

import com.graphql_security.entities.User;
import com.graphql_security.exception.CustomException;
import com.graphql_security.exception.ErrorCode;
import com.graphql_security.repository.UserRepository;
import com.graphql_security.util.AuthorityUtil;
import com.graphql_security.util.JwtTokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;

    public JwtAuthenticationFilter(JwtTokenUtil jwtTokenUtil,
                                   UserRepository userRepository) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = jwtTokenUtil.getJwtFromRequest(request);

            if (Objects.nonNull(jwt) && jwtTokenUtil.validateToken(jwt)) {
                String username = jwtTokenUtil.getUsernameFromToken(jwt);
                User user = userRepository.findByUsername(username).orElseThrow();

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        user,
                        user.getPassword(),
                        AuthorityUtil.getAuthorities(user)
                );
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception ex) {
            throw new CustomException(ErrorCode.AUTH_SECURITY_CONTEXT_ERROR, ex);
        }

        filterChain.doFilter(request, response);
    }

} 