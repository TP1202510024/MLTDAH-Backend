package pe.edu.upc.MLTDAH.students.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.MLTDAH.students.domain.model.entities.Gender;
import pe.edu.upc.MLTDAH.students.domain.model.valueobjects.Genders;

import java.util.List;
import java.util.Optional;

public interface GenderRepository extends JpaRepository<Gender, Long> {
    List<Gender> findAll();
    Optional<Gender> findById(Long id);
    boolean existsByName(Genders name);
}
