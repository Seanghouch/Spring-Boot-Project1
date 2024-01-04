package com.example.security.dto.response;

import lombok.Data;

@Data
public class ListResponse {

    private Object list;
    private Long total;

    public ListResponse(Object list, Long total){
        this.list = list;
        this.total = total;
    }

}
