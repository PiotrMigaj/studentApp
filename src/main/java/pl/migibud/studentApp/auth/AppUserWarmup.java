package pl.migibud.studentApp.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
        initializeUsers(initialUsersConfiguration.getUsers());
    }

    private void initializeUsers(List<DefaultUser> users) {
        users.stream()
                .filter(defaultUser -> !appUserRepository.existsByUsername(defaultUser.getUsername()))
                .map(this::mapDefaultUserToAppUser)
                .forEach(appUserRepository::save);
    }

    private AppUser mapDefaultUserToAppUser(DefaultUser defaultUser) {
        Set<AppUserRole> userRoles = defaultUser.getRoles()
                .stream()
                .map(appUserRoleRepository::findByName)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());

        return AppUser.builder()
                .password(passwordEncoder.encode(defaultUser.getPassword()))
                .username(defaultUser.getUsername())
                .roles(userRoles)
                .credentialsNonExpired(true)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .enabled(true)
                .build();
    }
    public void initializeRoles(List<String> roleNames){
        roleNames.stream()
                .filter(name->!appUserRoleRepository.existsByName(name))
                .forEach(name->appUserRoleRepository.save(new AppUserRole(name)));
    }

}
