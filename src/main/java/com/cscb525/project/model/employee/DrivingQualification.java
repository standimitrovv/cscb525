package com.cscb525.project.model.employee;

import lombok.Getter;

@Getter
public enum DrivingQualification {
    SPECIAL_SHIPMENTS("Special shipments"),
    EASILY_INFLAMMABLE("Easily inflammable"),
    MORE_THAN_12("More than 12 people");

    private final String type;

    DrivingQualification(String type){
        this.type = type;
    }
}
