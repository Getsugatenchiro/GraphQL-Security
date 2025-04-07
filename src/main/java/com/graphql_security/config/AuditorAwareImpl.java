package com.graphql_security.config;

import com.graphql_security.entities.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
@EnableJpaAuditing
public class AuditorAwareImpl implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (Objects.isNull(authentication) || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken)
            return Optional.empty();

        User user = (User) authentication.getPrincipal();
        return Optional.of(user.getId());
    }
}