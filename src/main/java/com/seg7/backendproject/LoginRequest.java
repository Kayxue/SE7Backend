package com.seg7.backendprojec;
import javax.validation.constraints.NotNull;

public class LoginRequest {
    @NotNull
    private String email;
    @NotNull
    private String password;

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }
}
