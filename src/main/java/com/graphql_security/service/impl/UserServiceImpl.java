package com.graphql_security.service.impl;

import com.graphql_security.dto.PageRequest;
import com.graphql_security.dto.PageResponse;
import com.graphql_security.dto.UserUpdateRequest;
import com.graphql_security.entities.User;
import com.graphql_security.mapper.UserMapper;
import com.graphql_security.repository.UserRepository;
import com.graphql_security.service.UserService;
import com.graphql_security.util.AuthorityUtil;
import com.graphql_security.util.PageableUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow();

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                AuthorityUtil.getAuthorities(user)
        );
    }

    @Override
    public PageResponse getAll(PageRequest pageRequest) {
        org.springframework.data.domain.PageRequest pageable = PageableUtil.getPageable(pageRequest);
        Page<User> page = userRepository.findAll(pageable);
        return new PageResponse(
                page.getContent(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.getNumber(),
                page.getNumberOfElements()
        );
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public void delete(Long id) {
        User user = getById(id);
        userRepository.delete(user);
    }

    @Override
    public User update(UserUpdateRequest userUpdateRequest) {
        User user = getById(userUpdateRequest.id());
        User updatedUser = userMapper.partialUpdate(userUpdateRequest, user);
        return userRepository.save(updatedUser);
    }

}