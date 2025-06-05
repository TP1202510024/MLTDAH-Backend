package pe.edu.upc.MLTDAH.iam.interfaces.rest.transform;

import pe.edu.upc.MLTDAH.iam.domain.model.commands.UpdateUserCommand;
import pe.edu.upc.MLTDAH.iam.interfaces.rest.resources.UpdateUserResource;

public class UpdateUserCommandFromResourceAssembler {
    public static UpdateUserCommand toCommandFromResource(UpdateUserResource resource) {
        return new UpdateUserCommand(resource.firstName(), resource.lastName(), resource.dni(), resource.birthDate(), resource.photo());
    }
}
