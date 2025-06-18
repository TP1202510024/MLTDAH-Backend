package pe.edu.upc.MLTDAH.tests.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.MLTDAH.tests.domain.model.aggregates.Exam;
import pe.edu.upc.MLTDAH.tests.domain.model.entities.Question;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findAll();
    List<Question> findAllByExamId(Long examId);
    Optional<Question> findById(Long id);
}
