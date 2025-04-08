package com.graphql_security.dto;

import java.util.List;

public record PageResponse(List<?> list,
                           long size,
                           int totalPageSize,
                           int currentPageNumber,
                           int currentPageElements) {
}
