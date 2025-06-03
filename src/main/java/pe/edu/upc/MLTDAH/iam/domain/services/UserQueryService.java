package pe.edu.upc.MLTDAH.iam.domain.services;

import pe.edu.upc.MLTDAH.iam.domain.model.aggregates.User;
import pe.edu.upc.MLTDAH.iam.domain.model.queries.GetAllUsersByInstitutionIdQuery;
import pe.edu.upc.MLTDAH.iam.domain.model.queries.GetAllUsersQuery;
import pe.edu.upc.MLTDAH.iam.domain.model.queries.GetUserByIdQuery;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {
    List<User> handle(GetAllUsersQuery query);
    List<User> handle(GetAllUsersByInstitutionIdQuery query);
    Optional<User> handle(GetUserByIdQuery query);
}
