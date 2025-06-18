package pe.edu.upc.MLTDAH.tests.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.MLTDAH.tests.domain.model.aggregates.Test;
import pe.edu.upc.MLTDAH.tests.domain.model.queries.GetAllTestByStudentIdAndYearQuery;
import pe.edu.upc.MLTDAH.tests.domain.model.queries.GetAllTestsByStudentIdQuery;
import pe.edu.upc.MLTDAH.tests.domain.model.queries.GetAllTestsQuery;
import pe.edu.upc.MLTDAH.tests.domain.model.queries.GetTestByIdQuery;
import pe.edu.upc.MLTDAH.tests.domain.services.TestQueryService;
import pe.edu.upc.MLTDAH.tests.infrastructure.persistence.jpa.TestRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TestQueryServiceImplementation implements TestQueryService {
    private final TestRepository testRepository;

    public TestQueryServiceImplementation(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @Override
    public List<Test> handle(GetAllTestsQuery query) {
        return testRepository.findAll();
    }

    @Override
    public List<Test> handle(GetAllTestsByStudentIdQuery query) {
        return testRepository.findAllByStudentId(query.studentId());
    }

    @Override
    public List<Test> handle(GetAllTestByStudentIdAndYearQuery query) {
        return testRepository.findByStudentIdAndYear(query.studentId(), query.year());
    }

    @Override
    public Optional<Test> handle(GetTestByIdQuery query) {
        return testRepository.findById(query.id());
    }
}
