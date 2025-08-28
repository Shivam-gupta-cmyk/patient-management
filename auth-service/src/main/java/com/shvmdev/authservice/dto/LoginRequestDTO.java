package com.shvmdev.authservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;

@Component
public class LoginRequestDTO {

    @NotBlank(message = "Email is required")
    @Email(message="Email should be a valid email adddress")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min=8, message = "Password must be atleastv 8 characters long")
    private String password;

    public @NotBlank(message = "Email is required") @Email(message = "Email should be a valid email adddress") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email is required") @Email(message = "Email should be a valid email adddress") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Password is required") @Size(min = 8, message = "Password must be atleastv 8 characters long") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password is required") @Size(min = 8, message = "Password must be atleastv 8 characters long") String password) {
        this.password = password;
    }

}
