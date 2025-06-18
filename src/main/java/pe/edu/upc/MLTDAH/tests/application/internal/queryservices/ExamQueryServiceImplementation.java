package pe.edu.upc.MLTDAH.tests.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.MLTDAH.tests.domain.model.aggregates.Exam;
import pe.edu.upc.MLTDAH.tests.domain.model.queries.GetAllExamsQuery;
import pe.edu.upc.MLTDAH.tests.domain.model.queries.GetExamByIdQuery;
import pe.edu.upc.MLTDAH.tests.domain.services.ExamQueryService;
import pe.edu.upc.MLTDAH.tests.infrastructure.persistence.jpa.ExamRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ExamQueryServiceImplementation implements ExamQueryService {
    private final ExamRepository examRepository;

    public ExamQueryServiceImplementation(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    @Override
    public List<Exam> handle(GetAllExamsQuery query) {
        return examRepository.findAll();
    }

    @Override
    public Optional<Exam> handle(GetExamByIdQuery query) {
        return examRepository.findById(query.id());
    }
}
