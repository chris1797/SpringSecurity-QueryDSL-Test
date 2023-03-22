package com.security.demo.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    private String memberid;
    private String password;

}
