package ua.dovhopoliuk.springtask.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN,
    MODER,
    SPEAKER,
    USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
