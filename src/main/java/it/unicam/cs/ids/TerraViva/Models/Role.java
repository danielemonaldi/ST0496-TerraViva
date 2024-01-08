package it.unicam.cs.ids.TerraViva.Models;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    AUTHORIZED_TOURIST,
    CONTRIBUTOR,
    AUTHORIZED_CONTRIBUTOR,
    ENTERTAINER,
    AUTHORIZED_ENTERTAINER,
    MANAGER,
    CURATOR;

    @Override
    public String getAuthority() {
        return name();
    }
}