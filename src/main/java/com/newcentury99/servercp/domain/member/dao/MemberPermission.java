package com.newcentury99.servercp.domain.member.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberPermission {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private final String value;
}
