package com.graphql_security.util;

import com.graphql_security.dto.PageRequest;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Sort;

import java.util.Objects;

@UtilityClass
public class PageableUtil {

    public org.springframework.data.domain.PageRequest getPageable(PageRequest pageRequest) {
        return (Objects.nonNull(pageRequest.pageNo()) && Objects.nonNull(pageRequest.pageSize()) && Objects.nonNull(pageRequest.sort()) && Objects.nonNull(pageRequest.sortDirection())) ?
                org.springframework.data.domain.PageRequest.of(pageRequest.pageNo(), pageRequest.pageSize(), getSort(pageRequest.sort(), pageRequest.sortDirection())) :
                org.springframework.data.domain.PageRequest.of(0, Integer.MAX_VALUE, getSort("id", "ASC"));
    }

    private Sort getSort(String sort, String sortDirection) {
        Sort.Direction direction = Sort.Direction.fromString(sortDirection);
        Sort.Order order = new Sort.Order(direction, sort);

        return Sort.by(order);
    }

}
