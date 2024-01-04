package com.example.security.dto.request;

import com.example.security.dto.request.FilterPaging;
import lombok.Data;

import java.util.List;

@Data
public class GroupFilterPaging {

    private String filterType = "AND"; // OR, AND
    private List<FilterPaging> filterPaging;

    public GroupFilterPaging(String filterType, List<FilterPaging> filterPaging){
        this.setFilterType(filterType);
        this.setFilterPaging(filterPaging);
    }

}
