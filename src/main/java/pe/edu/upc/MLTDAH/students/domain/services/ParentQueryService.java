package pe.edu.upc.MLTDAH.students.domain.services;

import pe.edu.upc.MLTDAH.iam.domain.model.aggregates.User;
import pe.edu.upc.MLTDAH.students.domain.model.aggregates.Parent;
import pe.edu.upc.MLTDAH.students.domain.model.queries.GetParentByStudentIdQuery;
import pe.edu.upc.MLTDAH.students.domain.model.queries.GetStudentByParentIdQuery;

import java.util.Optional;

public interface ParentQueryService {
    Optional<Parent> handle(GetParentByStudentIdQuery query);
    Optional<Parent> handle(GetStudentByParentIdQuery query);
}
