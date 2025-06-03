package pe.edu.upc.MLTDAH.iam.domain.services;

import pe.edu.upc.MLTDAH.iam.domain.model.aggregates.Institution;
import pe.edu.upc.MLTDAH.iam.domain.model.queries.GetAllInstitutionsQuery;
import pe.edu.upc.MLTDAH.iam.domain.model.queries.GetInstitutionByIdQuery;

import java.util.List;
import java.util.Optional;

public interface InstitutionQueryService {
    List<Institution> handle(GetAllInstitutionsQuery query);
    Optional<Institution> handle(GetInstitutionByIdQuery query);
}
