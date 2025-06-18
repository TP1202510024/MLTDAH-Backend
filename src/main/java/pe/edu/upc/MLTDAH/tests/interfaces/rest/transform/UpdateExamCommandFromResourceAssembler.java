package pe.edu.upc.MLTDAH.tests.interfaces.rest.transform;

import pe.edu.upc.MLTDAH.tests.domain.model.commands.UpdateExamCommand;
import pe.edu.upc.MLTDAH.tests.interfaces.rest.resources.UpdateExamResource;

public class UpdateExamCommandFromResourceAssembler {
    public static UpdateExamCommand toCommandFromResource(UpdateExamResource resource) {
        return new UpdateExamCommand(resource.title(), resource.description());
    }
}
