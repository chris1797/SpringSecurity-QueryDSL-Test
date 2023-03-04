package com.zaritalk.demo.config;

import com.zaritalk.demo.entity.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    private String memberid;
    private String password;

}
