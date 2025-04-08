package com.graphql_security.service;

import com.graphql_security.dto.PageRequest;
import com.graphql_security.dto.PageResponse;
import com.graphql_security.dto.UserUpdateRequest;
import com.graphql_security.entities.User;

public interface UserService {

    PageResponse getAll(PageRequest pageRequest);

    User getById(Long id);

    void delete(Long id);

    User update(UserUpdateRequest userUpdateRequest);

}
