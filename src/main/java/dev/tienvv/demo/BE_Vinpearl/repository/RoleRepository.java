package dev.tienvv.demo.BE_Vinpearl.repository;

import dev.tienvv.demo.BE_Vinpearl.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
