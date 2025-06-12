package pe.edu.upc.MLTDAH.students.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.MLTDAH.students.domain.model.entities.SchoolGrade;
import pe.edu.upc.MLTDAH.students.domain.model.valueobjects.SchoolGrades;

import java.util.List;
import java.util.Optional;

@Repository
public interface SchoolGradeRepository extends JpaRepository<SchoolGrade, Long> {
    List<SchoolGrade> findAll();
    Optional<SchoolGrade> findById(Long id);
    boolean existsByName(SchoolGrades name);
}
