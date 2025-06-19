package pe.edu.upc.MLTDAH.iam.interfaces.rest.transform;

import pe.edu.upc.MLTDAH.iam.domain.model.commands.GenerateForgotPasswordCommand;
import pe.edu.upc.MLTDAH.iam.interfaces.rest.resources.GenerateForgotPasswordResource;

public class GenerateForgotPasswordCommandFromResourceAssembler {
    public static GenerateForgotPasswordCommand toCommandFromResource(GenerateForgotPasswordResource resource) {
        return new GenerateForgotPasswordCommand(resource.email());
    }
}
