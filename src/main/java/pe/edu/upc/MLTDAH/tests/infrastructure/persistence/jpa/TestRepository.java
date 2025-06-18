package pe.edu.upc.MLTDAH.tests.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.MLTDAH.tests.domain.model.aggregates.Test;

import java.util.List;
import java.util.Optional;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
    List<Test> findAll();
    List<Test> findAllByStudentId(Long studentId);
    @Query("SELECT t FROM Test t WHERE t.studentId = :studentId AND FUNCTION('YEAR', t.createdAt) = :year")
    List<Test> findByStudentIdAndYear(@Param("studentId") Long studentId, @Param("year") Long year);
    Optional<Test> findById(Long id);
}
