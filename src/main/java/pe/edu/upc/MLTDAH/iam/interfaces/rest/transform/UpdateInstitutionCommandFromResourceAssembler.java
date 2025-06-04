package pe.edu.upc.MLTDAH.iam.interfaces.rest.transform;

import pe.edu.upc.MLTDAH.iam.domain.model.commands.UpdateInstitutionCommand;
import pe.edu.upc.MLTDAH.iam.interfaces.rest.resources.UpdateInstitutionResource;

public class UpdateInstitutionCommandFromResourceAssembler {
    public static UpdateInstitutionCommand toCommandFromResource(UpdateInstitutionResource resource) {
        return new UpdateInstitutionCommand(resource.name(), resource.creationDate(), resource.address(), resource.photo());
    }
}
