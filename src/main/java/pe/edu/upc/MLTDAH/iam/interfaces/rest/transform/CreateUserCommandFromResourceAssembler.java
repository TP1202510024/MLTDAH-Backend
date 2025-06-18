package pe.edu.upc.MLTDAH.iam.interfaces.rest.transform;

import pe.edu.upc.MLTDAH.iam.domain.model.commands.CreateUserCommand;
import pe.edu.upc.MLTDAH.iam.interfaces.rest.resources.CreateUserResource;

public class CreateUserCommandFromResourceAssembler {
    public static CreateUserCommand toCommandFromResource(CreateUserResource resource) {
        return new CreateUserCommand(resource.firstName(), resource.lastName(), resource.dni(), resource.birthDate(), resource.photo(), resource.email(), resource.password(), resource.institutionId(), resource.roleId());
    }
}
