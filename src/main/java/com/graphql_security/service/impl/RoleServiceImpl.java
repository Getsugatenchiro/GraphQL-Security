package com.graphql_security.service.impl;

import com.graphql_security.dto.PageRequest;
import com.graphql_security.dto.RoleCreateRequest;
import com.graphql_security.dto.RoleUpdateRequest;
import com.graphql_security.entities.Role;
import com.graphql_security.mapper.RoleMapper;
import com.graphql_security.repository.RoleRepository;
import com.graphql_security.service.RoleService;
import com.graphql_security.util.PageableUtil;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @PostConstruct
    public void setDefaultRole() {
        if (!roleRepository.existsByName("ADMIN"))
            roleRepository.save(new Role("ADMIN"));
    }

    @Override
    public Page<Role> getAll(PageRequest pageRequest) {
        org.springframework.data.domain.PageRequest pageable = PageableUtil.getPageable(pageRequest);
        return roleRepository.findAll(pageable);
    }

    @Override
    public Role getById(Long id) {
        return roleRepository.findById(id).orElseThrow();
    }

    @Override
    public void delete(Long id) {
        Role role = getById(id);
        roleRepository.delete(role);
    }

    @Override
    public Role create(RoleCreateRequest roleCreateRequest) {
        Role role = roleMapper.toEntity(roleCreateRequest);
        return roleRepository.save(role);
    }

    @Override
    public Role update(RoleUpdateRequest roleUpdateRequest) {
        Role role = getById(roleUpdateRequest.id());
        role.getPermissions().clear();
        roleRepository.save(role);
        return roleRepository.save(roleMapper.partialUpdate(roleUpdateRequest, role));

    }

}
