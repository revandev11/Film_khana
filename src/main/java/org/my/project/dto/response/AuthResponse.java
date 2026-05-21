package org.my.project.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {
        private String message;
        private UserResponse user;
        private String token;
        public AuthResponse(String token,UserResponse user) {
            this.user = user;
            this.token=token;
            this.message = "User successfully registered";
        }
}
