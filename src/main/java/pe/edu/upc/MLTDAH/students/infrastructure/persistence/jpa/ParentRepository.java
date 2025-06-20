package pe.edu.upc.MLTDAH.students.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.MLTDAH.students.domain.model.aggregates.Parent;

import java.util.Optional;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Long> {
    Optional<Parent> findByStudentId(Long studentId);
    Optional<Parent> findByParentId(Long parentId);
}
