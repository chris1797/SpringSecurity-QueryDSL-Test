package com.security.demo.common.constants;

public enum Role {

    /**
     * 서비스 권한별 Enum 값
     */
    REALTOR("공인중개사"),
    LESSOR("임대인"),
    LESSEE("임차인");

    Role (String roleCode) {
    }
}
