package pe.edu.upc.MLTDAH.iam.interfaces.rest.transform;

import pe.edu.upc.MLTDAH.iam.domain.model.commands.CreateInstitutionCommand;
import pe.edu.upc.MLTDAH.iam.interfaces.rest.resources.CreateInstitutionResource;

public class CreateInstitutionCommandFromResourceAssembler {
    public static CreateInstitutionCommand toCommandFromResource(CreateInstitutionResource resource) {
        return new CreateInstitutionCommand(resource.name(), resource.creationDate(), resource.address(), resource.photoInstitution());
    }
}
