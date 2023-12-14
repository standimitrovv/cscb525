package com.cscb525.project.model.transportCompany;

import lombok.Getter;

@Getter
public enum SortingAndFilteringCriteria {
    NAME("name"),
    REVENUE("revenue"),
    NONE("none");

    private final String criteriaType;

    SortingAndFilteringCriteria(String criteriaType){
        this.criteriaType = criteriaType;
    }
}
