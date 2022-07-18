package peaksoft.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.springboot.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRoleName(String roleName);
}
