package pe.edu.upc.MLTDAH.tests.domain.services;

import pe.edu.upc.MLTDAH.tests.domain.model.entities.Question;
import pe.edu.upc.MLTDAH.tests.domain.model.queries.GetAllQuestionsByExamIdQuery;
import pe.edu.upc.MLTDAH.tests.domain.model.queries.GetAllQuestionsQuery;
import pe.edu.upc.MLTDAH.tests.domain.model.queries.GetQuestionByIdQuery;

import java.util.List;
import java.util.Optional;

public interface QuestionQueryService {
    List<Question> handle(GetAllQuestionsQuery query);
    List<Question> handle(GetAllQuestionsByExamIdQuery query);
    Optional<Question> handle(GetQuestionByIdQuery query);
}
