package ua.dovhopoliuk.springtask.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,
    ADMIN,
    MODER,
    SPEAKER;

    @Override
    public String getAuthority() {
        return name();
    }
}
