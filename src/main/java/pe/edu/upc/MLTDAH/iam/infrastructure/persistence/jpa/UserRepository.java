package pe.edu.upc.MLTDAH.iam.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.MLTDAH.iam.domain.model.aggregates.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();
    List<User> findAllByInstitutionId(Long institutionId);
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    Optional<User> findByRestartCode(String restartCode);
}
