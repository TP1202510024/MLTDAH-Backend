package pe.edu.upc.MLTDAH.iam.interfaces.rest.transform;

import pe.edu.upc.MLTDAH.iam.domain.model.commands.CreateInstitutionCommand;
import pe.edu.upc.MLTDAH.iam.domain.model.commands.SignUpCommand;
import pe.edu.upc.MLTDAH.iam.interfaces.rest.resources.InstitutionResource;
import pe.edu.upc.MLTDAH.iam.interfaces.rest.resources.SignUpResource;

public class SignUpCommandFromResourceAssembler {
    public static SignUpCommand toUserCommandFromResource(SignUpResource resource, Long institutionId) {
        return new SignUpCommand(resource.firstName(), resource.lastName(), resource.dni(), resource.birthDate(), resource.photoUser(), resource.email(), resource.password(), institutionId);
    }
    public static CreateInstitutionCommand toInstitutionCommandFromResource(SignUpResource resource) {
        return new CreateInstitutionCommand(resource.name(), resource.creationDate(), resource.address(), resource.photoInstitution());
    }
}
