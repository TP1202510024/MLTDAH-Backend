package pe.edu.upc.MLTDAH.iam.interfaces.rest.transform;

import pe.edu.upc.MLTDAH.iam.domain.model.commands.SignUpCommand;
import pe.edu.upc.MLTDAH.iam.interfaces.rest.resources.SignUpResource;

public class SignUpCommandFromResourceAssembler {
    public static SignUpCommand toCommandFromResource(SignUpResource resource) {
        return new SignUpCommand(resource.firstName(), resource.lastName(), resource.dni(), resource.birthDate(), resource.photoUser(), resource.email(), resource.password(), resource.institutionId());
    }
}
