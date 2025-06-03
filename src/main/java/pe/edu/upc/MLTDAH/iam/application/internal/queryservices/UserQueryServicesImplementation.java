package pe.edu.upc.MLTDAH.iam.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.MLTDAH.iam.domain.model.aggregates.User;
import pe.edu.upc.MLTDAH.iam.domain.model.queries.GetAllUsersByInstitutionIdQuery;
import pe.edu.upc.MLTDAH.iam.domain.model.queries.GetAllUsersQuery;
import pe.edu.upc.MLTDAH.iam.domain.model.queries.GetUserByIdQuery;
import pe.edu.upc.MLTDAH.iam.domain.services.UserQueryService;
import pe.edu.upc.MLTDAH.iam.infrastructure.persistence.jpa.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserQueryServicesImplementation implements UserQueryService {
    private final UserRepository userRepository;

    public UserQueryServicesImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> handle(GetAllUsersQuery query) {
        return this.userRepository.findAll();
    }

    @Override
    public List<User> handle(GetAllUsersByInstitutionIdQuery query) {
        return this.userRepository.findAllByInstitutionId(query.id());
    }

    @Override
    public Optional<User> handle(GetUserByIdQuery query) {
        return this.userRepository.findById(query.id());
    }
}
