package com.example.security.dto.request;

import lombok.Data;

@Data
public class FilterPaging {

    private String column;
    private Object value;
    private String operation;

    public FilterPaging(String column, Object value, String operation){
        this.setColumn(column);
        this.setValue(value);
        this.setOperation(operation);
    }

}
