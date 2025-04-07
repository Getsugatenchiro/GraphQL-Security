package com.graphql_security.dto;

public record PageRequest(Integer pageNo,
                          Integer pageSize,
                          String sort,
                          String sortDirection) {
}
