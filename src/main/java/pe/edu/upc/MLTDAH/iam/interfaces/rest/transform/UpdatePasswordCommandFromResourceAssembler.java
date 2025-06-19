package pe.edu.upc.MLTDAH.iam.interfaces.rest.transform;

import pe.edu.upc.MLTDAH.iam.domain.model.commands.UpdatePasswordCommand;
import pe.edu.upc.MLTDAH.iam.interfaces.rest.resources.UpdatePasswordResource;

public class UpdatePasswordCommandFromResourceAssembler {
    public static UpdatePasswordCommand toCommandFromResource(UpdatePasswordResource resource) {
        return new UpdatePasswordCommand(resource.password(), resource.repeatedPassword(), resource.restartCode());
    }
}
