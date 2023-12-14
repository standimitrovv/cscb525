package com.cscb525.project.model.transportCompany;

import lombok.Getter;

@Getter
public enum SortType {
    ASC("asc"),
    DESC("desc"),
    NONE("none");

    private final String sortType;

    SortType(String sortType){
        this.sortType = sortType;
    }
}
