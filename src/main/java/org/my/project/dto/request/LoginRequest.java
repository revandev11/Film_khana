package org.my.project.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @NotBlank(message = "email can not be empty")
    @Email(message = "Import correct email format")
    private String email;

    @NotBlank(message = "Password can not be empty")
    @Size(min = 6, message = "Passwor must be 6 characters at least")
    private String password;

}