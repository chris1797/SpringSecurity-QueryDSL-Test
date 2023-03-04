package com.security.demo.entity;

public enum Role {

    /**
     * 서비스 권한별 Enum 값
     */
    ROLE_REALTOR("공인중개사"),
    ROLE_LESSOR("임대인"),
    ROLE_LESSEE("임차인");

    private String roleCode;

    Role (String roleCode) {
        this.roleCode = roleCode;
    }
}
