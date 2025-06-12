package pe.edu.upc.MLTDAH.students.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.MLTDAH.students.domain.model.aggregates.Student;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findAll();
    List<Student> findByInstitutionId(Long institutionId);
    List<Student> findByInstitutionIdAndSchoolGradeId(Long institutionId, Long schoolGradeId);
    List<Student> findByInstitutionIdAndGenderId(Long institutionId, Long genderId);
    List<Student> findByInstitutionIdAndGenderIdAndSchoolGradeId(Long institutionId, Long genderId, Long schoolGradeId);
    Optional<Student> findById(Long id);
}
