package pe.edu.upc.MLTDAH.tests.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.MLTDAH.tests.domain.model.entities.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
