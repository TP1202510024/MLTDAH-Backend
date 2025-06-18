package pe.edu.upc.MLTDAH.tests.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.MLTDAH.tests.domain.model.entities.Question;
import pe.edu.upc.MLTDAH.tests.domain.model.queries.GetAllQuestionsByExamIdQuery;
import pe.edu.upc.MLTDAH.tests.domain.model.queries.GetAllQuestionsQuery;
import pe.edu.upc.MLTDAH.tests.domain.model.queries.GetQuestionByIdQuery;
import pe.edu.upc.MLTDAH.tests.domain.services.QuestionQueryService;
import pe.edu.upc.MLTDAH.tests.infrastructure.persistence.jpa.QuestionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionQueryServiceImplementation implements QuestionQueryService {
    private final QuestionRepository questionRepository;

    public QuestionQueryServiceImplementation(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public List<Question> handle(GetAllQuestionsQuery query) {
        return questionRepository.findAll();
    }

    @Override
    public List<Question> handle(GetAllQuestionsByExamIdQuery query) {
        return questionRepository.findAllByExamId(query.examId());
    }

    @Override
    public Optional<Question> handle(GetQuestionByIdQuery query) {
        return questionRepository.findById(query.id());
    }
}
