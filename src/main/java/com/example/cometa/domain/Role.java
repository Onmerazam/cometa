package com.example.cometa.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    RC_EMPLOYEE, DTK, USER, ADMIN;

    @Override
    public String getAuthority(){
        return name();
    };
}
