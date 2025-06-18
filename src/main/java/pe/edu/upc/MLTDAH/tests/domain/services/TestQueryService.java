package pe.edu.upc.MLTDAH.tests.domain.services;

import pe.edu.upc.MLTDAH.tests.domain.model.aggregates.Test;
import pe.edu.upc.MLTDAH.tests.domain.model.queries.GetAllTestByStudentIdAndYearQuery;
import pe.edu.upc.MLTDAH.tests.domain.model.queries.GetAllTestsByStudentIdQuery;
import pe.edu.upc.MLTDAH.tests.domain.model.queries.GetAllTestsQuery;
import pe.edu.upc.MLTDAH.tests.domain.model.queries.GetTestByIdQuery;

import java.util.List;
import java.util.Optional;

public interface TestQueryService {
    List<Test> handle(GetAllTestsQuery query);
    List<Test> handle(GetAllTestsByStudentIdQuery query);
    List<Test> handle(GetAllTestByStudentIdAndYearQuery query);
    Optional<Test> handle(GetTestByIdQuery query);
}
