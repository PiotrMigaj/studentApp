package pl.migibud.studentApp.auth;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Getter
@Setter
@ToString
@Configuration
@ConfigurationProperties(prefix = "application.default")
public class InitialUsersConfiguration {
    private List<String> roles;
    private List<DefaultUser> users;
}
