package pe.edu.upc.MLTDAH.tests.domain.services;

import pe.edu.upc.MLTDAH.tests.domain.model.aggregates.Exam;
import pe.edu.upc.MLTDAH.tests.domain.model.queries.GetAllExamsQuery;
import pe.edu.upc.MLTDAH.tests.domain.model.queries.GetExamByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ExamQueryService {
    List<Exam> handle(GetAllExamsQuery query);
    Optional<Exam> handle(GetExamByIdQuery query);
}
