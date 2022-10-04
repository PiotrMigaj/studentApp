package pl.migibud.studentApp.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
class DefaultUser {
    private String username;
    private String password;
    private List<String> roles;
}
