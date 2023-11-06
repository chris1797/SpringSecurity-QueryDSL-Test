package com.security.demo.domain.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    private String accountId;
    private String password;

}
