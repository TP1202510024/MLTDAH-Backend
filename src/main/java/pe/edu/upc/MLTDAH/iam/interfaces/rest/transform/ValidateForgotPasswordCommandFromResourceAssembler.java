package pe.edu.upc.MLTDAH.iam.interfaces.rest.transform;

import pe.edu.upc.MLTDAH.iam.domain.model.commands.ValidateForgotPasswordCommand;
import pe.edu.upc.MLTDAH.iam.interfaces.rest.resources.ValidateForgotPasswordResource;

public class ValidateForgotPasswordCommandFromResourceAssembler {
    public static ValidateForgotPasswordCommand toCommandFromResource(ValidateForgotPasswordResource resource) {
        return new ValidateForgotPasswordCommand(resource.restartCode());
    }
}
