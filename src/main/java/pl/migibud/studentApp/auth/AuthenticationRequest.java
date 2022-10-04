package pl.migibud.studentApp.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class AuthenticationRequest {
    private String login;
    private String pass;
}
