package com.aixenhuh.portfolio.common.security;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum SecurityRole {
    ADMIN("ROLE_ADMIN", "관리자권한"),
    USER("ROLE_USER", "사용자권한"),
    UNKNOWN("UNKNOWN", "알수없는 권한");

    private String code;
    private String description;

    SecurityRole(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public static SecurityRole of(String code) {
        return Arrays.stream(SecurityRole.values())
                .filter(r -> r.getCode().equals(code))
                .findAny()
                .orElse(UNKNOWN);
    }
}
