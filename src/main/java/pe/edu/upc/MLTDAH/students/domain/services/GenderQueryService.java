package pe.edu.upc.MLTDAH.students.domain.services;

import pe.edu.upc.MLTDAH.students.domain.model.entities.Gender;
import pe.edu.upc.MLTDAH.students.domain.model.queries.GetAllGendersQuery;
import pe.edu.upc.MLTDAH.students.domain.model.queries.GetGenderByIdQuery;

import java.util.List;
import java.util.Optional;

public interface GenderQueryService {
    List<Gender> handle(GetAllGendersQuery query);
    Optional<Gender> handle(GetGenderByIdQuery query);
}
