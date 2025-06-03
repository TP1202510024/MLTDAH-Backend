package pe.edu.upc.MLTDAH.iam.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.MLTDAH.iam.domain.model.aggregates.Institution;
import pe.edu.upc.MLTDAH.iam.domain.model.queries.GetAllInstitutionsQuery;
import pe.edu.upc.MLTDAH.iam.domain.model.queries.GetInstitutionByIdQuery;
import pe.edu.upc.MLTDAH.iam.domain.services.InstitutionQueryService;
import pe.edu.upc.MLTDAH.iam.infrastructure.persistence.jpa.InstitutionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InstitutionQueryServiceImplementation implements InstitutionQueryService {
    private final InstitutionRepository institutionRepository;

    public InstitutionQueryServiceImplementation(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    @Override
    public List<Institution> handle(GetAllInstitutionsQuery query) {
        return this.institutionRepository.findAll();
    }

    @Override
    public Optional<Institution> handle(GetInstitutionByIdQuery query) {
        return this.institutionRepository.findById(query.id());
    }
}
