package com.security.demo.domain;

public enum Role {

    /**
     * 서비스 권한별 Enum 값
     */
    REALTOR("공인중개사"),
    LESSOR("임대인"),
    LESSEE("임차인");

    private String roleCode;

    Role (String roleCode) {
        this.roleCode = roleCode;
    }
}
