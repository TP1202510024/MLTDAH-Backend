package pe.edu.upc.MLTDAH.iam.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.MLTDAH.iam.domain.model.commands.SeedRolesCommand;
import pe.edu.upc.MLTDAH.iam.domain.model.entities.Role;
import pe.edu.upc.MLTDAH.iam.domain.model.valueobjects.Roles;
import pe.edu.upc.MLTDAH.iam.domain.services.RoleCommandService;
import pe.edu.upc.MLTDAH.iam.infrastructure.persistence.jpa.RoleRepository;

import java.util.Arrays;

@Service
public class RoleCommandServiceImplementation implements RoleCommandService {
    private final RoleRepository roleRepository;

    public RoleCommandServiceImplementation(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void handle(SeedRolesCommand command) {
        Arrays.stream(Roles.values()).forEach(role -> {
            if(!roleRepository.existsByName(role.name())) {
                roleRepository.save(new Role(Roles.valueOf(role.name())));
            }
        });
    }
}
