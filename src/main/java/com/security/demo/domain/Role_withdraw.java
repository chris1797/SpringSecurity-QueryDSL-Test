package com.security.demo.domain;

public enum Role_withdraw {

    WITHDRAW("탈퇴 유저"),
    ACTIVE("활성 유저");

    private String quitCode;

    Role_withdraw(String quitCode) {
        this.quitCode = quitCode;
    }
}
