package pe.edu.upc.MLTDAH.students.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.MLTDAH.students.domain.model.entities.Gender;
import pe.edu.upc.MLTDAH.students.domain.model.queries.GetAllGendersQuery;
import pe.edu.upc.MLTDAH.students.domain.model.queries.GetGenderByIdQuery;
import pe.edu.upc.MLTDAH.students.domain.services.GenderQueryService;
import pe.edu.upc.MLTDAH.students.infrastructure.persistence.jpa.GenderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GenderQueryServiceImplementation implements GenderQueryService {
    private final GenderRepository genderRepository;

    public GenderQueryServiceImplementation(GenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }

    @Override
    public List<Gender> handle(GetAllGendersQuery query) {
        return genderRepository.findAll();
    }

    @Override
    public Optional<Gender> handle(GetGenderByIdQuery query) {
        return genderRepository.findById(query.id());
    }
}
