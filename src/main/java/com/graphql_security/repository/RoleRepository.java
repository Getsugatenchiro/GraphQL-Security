package com.graphql_security.repository;

import com.graphql_security.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    boolean existsByName(String name);

}