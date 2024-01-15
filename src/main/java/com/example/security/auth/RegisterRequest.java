package com.example.security.auth;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotEmpty(message = "required")
    private String firstname;
    @NotEmpty(message = "required")
    private String lastname;
    @NotEmpty(message = "required")
    private String userName;
    @NotEmpty(message = "required")
    private String email;
    @NotEmpty(message = "required")
    private String password;
    private String imageUrl;
    private Boolean isActive = true;
    private Boolean isForceResetPassword = true;

}
