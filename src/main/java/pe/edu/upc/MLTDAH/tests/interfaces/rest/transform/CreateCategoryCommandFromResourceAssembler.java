package pe.edu.upc.MLTDAH.tests.interfaces.rest.transform;

import pe.edu.upc.MLTDAH.tests.domain.model.commands.CreateCategoryCommand;
import pe.edu.upc.MLTDAH.tests.interfaces.rest.resources.CreateCategoryResource;

public class CreateCategoryCommandFromResourceAssembler {
    public static CreateCategoryCommand toCommandFromResource(CreateCategoryResource resource) {
        return new CreateCategoryCommand(resource.name(), resource.description());
    }
}
