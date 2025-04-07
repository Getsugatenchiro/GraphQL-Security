package com.graphql_security.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
@EntityListeners(AuditingEntityListener.class)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    private Instant createdDate;

    @CreatedBy
    private Long creator;

    @LastModifiedDate
    private Instant updatedDate;

    @LastModifiedBy
    private Long updater;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Permission> permissions = new HashSet<>();

    public Role(String name) {
        this.name = name;
    }

}
