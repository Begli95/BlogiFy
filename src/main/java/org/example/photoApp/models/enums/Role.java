package org.example.photoApp.models.enums;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.GeneratedValue;

public enum Role implements GrantedAuthority {
    ROLE_USER, ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
