package pe.edu.upc.MLTDAH.students.domain.services;

import pe.edu.upc.MLTDAH.students.domain.model.aggregates.Student;
import pe.edu.upc.MLTDAH.students.domain.model.queries.GetAllStudentsByInstitutionIdAndGradeIdQuery;
import pe.edu.upc.MLTDAH.students.domain.model.queries.GetAllStudentsByInstitutionIdQuery;
import pe.edu.upc.MLTDAH.students.domain.model.queries.GetAllStudentsQuery;
import pe.edu.upc.MLTDAH.students.domain.model.queries.GetStudentByIdQuery;

import java.util.List;
import java.util.Optional;

public interface StudentQueryService {
    List<Student> handle(GetAllStudentsQuery query);
    List<Student> handle(GetAllStudentsByInstitutionIdQuery query);
    List<Student> handle(GetAllStudentsByInstitutionIdAndGradeIdQuery query);
    Optional<Student> handle(GetStudentByIdQuery query);
}
