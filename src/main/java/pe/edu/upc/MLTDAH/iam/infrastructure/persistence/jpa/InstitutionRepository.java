package pe.edu.upc.MLTDAH.iam.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.MLTDAH.iam.domain.model.aggregates.Institution;
import pe.edu.upc.MLTDAH.iam.domain.model.aggregates.User;

import java.util.List;
import java.util.Optional;

public interface InstitutionRepository extends JpaRepository<Institution, Long> {
    List<Institution> findAll();
    Optional<Institution> findById(Long id);
}
