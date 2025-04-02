package com.security.demo.domain.request;


import com.security.demo.common.constants.Role;

public record SignUpRequest(
        String email,
        String password,
        Role role) { }
