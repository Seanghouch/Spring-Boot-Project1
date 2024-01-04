package com.example.security.dto.request;

import lombok.Data;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Objects;

@Data
public class PagingRequest {

    private Integer pageNo = 0;
    private Integer pageSize = 100;
    private String sort = "DESC";
    private String sortBy = "id";

    public Pageable getPageable(PagingRequest dto){
        Integer page = Objects.nonNull(dto.getPageNo()) ? dto.getPageNo() : this.pageNo;
        Integer size = Objects.nonNull(dto.getPageSize()) ? dto.getPageSize() : this.pageSize;
        String sort = Objects.nonNull(dto.getSort()) ? dto.getSort() : this.sort;
        String sortBy = Objects.nonNull(dto.getSortBy()) ? dto.getSortBy() : this.sortBy;

        return org.springframework.data.domain.PageRequest.of(page, size, Sort.Direction.valueOf(sort), sortBy);
    }

}
