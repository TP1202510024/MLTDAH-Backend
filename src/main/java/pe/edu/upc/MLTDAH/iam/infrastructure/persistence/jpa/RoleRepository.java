package pe.edu.upc.MLTDAH.iam.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.MLTDAH.iam.domain.model.entities.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
    boolean existsByName(String name);
}
