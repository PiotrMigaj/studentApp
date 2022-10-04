package pl.migibud.studentApp.auth;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRoleRepository extends JpaRepository<AppUserRole,Long> {
    boolean existsByName(String roleName);
    Optional<AppUserRole> findByName(String roleName);
}
