package com.seg7.backendproject;

import javax.validation.constraints.NotNull;

public class RegisterRequest {
    @NotNull
    private String password;
    @NotNull
    private String email;

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }

}