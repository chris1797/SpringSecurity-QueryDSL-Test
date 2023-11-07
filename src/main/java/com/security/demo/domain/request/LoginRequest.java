package com.security.demo.domain.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class LoginRequest {

    private String accountId;
    private String password;

}
