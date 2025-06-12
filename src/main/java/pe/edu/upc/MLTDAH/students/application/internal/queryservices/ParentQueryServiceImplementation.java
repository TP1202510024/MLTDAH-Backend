package pe.edu.upc.MLTDAH.students.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.MLTDAH.students.domain.model.aggregates.Parent;
import pe.edu.upc.MLTDAH.students.domain.model.queries.GetParentByStudentIdQuery;
import pe.edu.upc.MLTDAH.students.domain.services.ParentQueryService;
import pe.edu.upc.MLTDAH.students.infrastructure.persistence.jpa.ParentRepository;

import java.util.Optional;

@Service
public class ParentQueryServiceImplementation implements ParentQueryService {
    private final ParentRepository parentRepository;

    public ParentQueryServiceImplementation(final ParentRepository parentRepository) {
        this.parentRepository = parentRepository;
    }

    @Override
    public Optional<Parent> handle(GetParentByStudentIdQuery query) {
        return parentRepository.findByStudentId(query.studentId());
    }
}
