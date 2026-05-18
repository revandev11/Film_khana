package org.my.project.dto.Request;

import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.my.project.enums.Roles;

@Getter
@Setter
public class UserCreateRequest {
    @NotBlank(message = "username is requered")
    @Size(min = 6, max=100, message = " username must be 2-200 characters")
    private String userName;
    @NotBlank
    @Email(message = "email format is not correct")
    @Size(min = 6, max=100, message = "Email must be 2-200 characters!")
    private String email;
    @NotNull
    @Size(min=8, message = "Password must have at least 6 characters!")
    private String password;
    @NotBlank
    private Roles roles;
    public UserCreateRequest(){}

}
