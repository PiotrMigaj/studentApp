package pl.migibud.studentApp.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
class AppUserWarmup {

    private final InitialUsersConfiguration initialUsersConfiguration;
    private final AppUserRepository appUserRepository;
    private final AppUserRoleRepository appUserRoleRepository;
    private final PasswordEncoder passwordEncoder;

    @EventListener(ContextRefreshedEvent.class)
    public void initializeDatabase(){
        log.info(String.format("Initial users: %s", initialUsersConfiguration));
        initializeRoles(initialUsersConfiguration.getRoles());
    }

    public void initializeRoles(List<String> roleNames){
        roleNames.stream()
                .filter(name->!appUserRoleRepository.existsByName(name))
                .forEach(name->appUserRoleRepository.save(new AppUserRole(name)));
    }

}
