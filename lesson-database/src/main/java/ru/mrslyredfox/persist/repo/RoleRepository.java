package ru.mrslyredfox.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mrslyredfox.persist.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
