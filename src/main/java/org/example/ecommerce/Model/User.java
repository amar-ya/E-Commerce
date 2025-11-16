package org.example.ecommerce.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User
{
    @NotEmpty(message = "User ID is required")
    private String id;
    @NotEmpty(message = "User name is required")
    @Size(min = 6, message = "username should be longer than 5 characters")
    private String username;
    @NotEmpty(message = "Password is required")
    @Size(min = 7, message = "password should be longer than 6 characters")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "password should contain only letters and numbers")
    private String password;
    @NotEmpty(message = "Email is required")
    @Email
    private String email;
    @NotEmpty(message = "Role is required")
    @Pattern(regexp = "^(Admin|Customer)$", message = "role should be either Admin or Customer")
    private String role;
    @Min(value = 0, message = "Balance cannot be negative")
    private double balance = 0;
}
