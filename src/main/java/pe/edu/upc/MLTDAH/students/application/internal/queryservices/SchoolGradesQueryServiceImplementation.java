package pe.edu.upc.MLTDAH.students.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.MLTDAH.students.domain.model.entities.SchoolGrade;
import pe.edu.upc.MLTDAH.students.domain.model.queries.GetAllSchoolGradesQuery;
import pe.edu.upc.MLTDAH.students.domain.model.queries.GetSchoolGradeByIdQuery;
import pe.edu.upc.MLTDAH.students.domain.services.SchoolGradeQueryService;
import pe.edu.upc.MLTDAH.students.infrastructure.persistence.jpa.SchoolGradeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SchoolGradesQueryServiceImplementation implements SchoolGradeQueryService {
    private final SchoolGradeRepository schoolGradeRepository;

    public SchoolGradesQueryServiceImplementation(SchoolGradeRepository schoolGradeRepository) {
        this.schoolGradeRepository = schoolGradeRepository;
    }

    @Override
    public List<SchoolGrade> handle(GetAllSchoolGradesQuery query) {
        return schoolGradeRepository.findAll();
    }

    @Override
    public Optional<SchoolGrade> handle(GetSchoolGradeByIdQuery query) {
        return schoolGradeRepository.findById(query.id());
    }
}
