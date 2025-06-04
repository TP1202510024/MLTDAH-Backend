package pe.edu.upc.MLTDAH.iam.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.MLTDAH.iam.domain.model.entities.Role;
import pe.edu.upc.MLTDAH.iam.domain.model.queries.GetAllRolesQuery;
import pe.edu.upc.MLTDAH.iam.domain.model.queries.GetRoleByNameQuery;
import pe.edu.upc.MLTDAH.iam.domain.services.RoleQueryService;
import pe.edu.upc.MLTDAH.iam.infrastructure.persistence.jpa.RoleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoleQueryServiceImplementation implements RoleQueryService {
    private final RoleRepository roleRepository;

    public RoleQueryServiceImplementation(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> handle(GetAllRolesQuery query) {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> handle(GetRoleByNameQuery query) {
        return roleRepository.findByName(query.name());
    }
}
