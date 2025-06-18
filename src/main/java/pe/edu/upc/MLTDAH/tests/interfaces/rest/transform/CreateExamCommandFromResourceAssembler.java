package pe.edu.upc.MLTDAH.tests.interfaces.rest.transform;

import pe.edu.upc.MLTDAH.tests.domain.model.commands.CreateExamCommand;
import pe.edu.upc.MLTDAH.tests.interfaces.rest.resources.CreateExamResource;

public class CreateExamCommandFromResourceAssembler {
    public static CreateExamCommand toCommandFromResource(CreateExamResource resource) {
        return new CreateExamCommand(resource.title(), resource.description());
    }
}
