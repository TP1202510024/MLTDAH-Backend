package pe.edu.upc.MLTDAH.students.domain.services;

import pe.edu.upc.MLTDAH.students.domain.model.entities.SchoolGrade;
import pe.edu.upc.MLTDAH.students.domain.model.queries.GetAllSchoolGradesQuery;
import pe.edu.upc.MLTDAH.students.domain.model.queries.GetSchoolGradeByIdQuery;

import java.util.List;
import java.util.Optional;

public interface SchoolGradeQueryService {
    List<SchoolGrade> handle(GetAllSchoolGradesQuery query);
    Optional<SchoolGrade> handle(GetSchoolGradeByIdQuery query);
}
