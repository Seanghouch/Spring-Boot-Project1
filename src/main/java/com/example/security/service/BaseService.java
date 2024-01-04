package com.example.security.service;

import com.example.security.dto.request.ListRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class BaseService {


    protected Pageable getPage(ListRequest pagingRequest){
        return new ListRequest().getPageable(pagingRequest);
    }

    protected static <T> Specification<T> filterColumn(ListRequest pagingRequest){
        FilterSpecification<T> filterSpecification = new FilterSpecification<T>();
        return filterSpecification.getSearchSpecification(pagingRequest.getFilters(), pagingRequest.getFilterType());
    }

}
