package pe.edu.upc.MLTDAH.tests.interfaces.rest.transform;

import pe.edu.upc.MLTDAH.tests.domain.model.commands.UpdateCategoryCommand;
import pe.edu.upc.MLTDAH.tests.interfaces.rest.resources.UpdateCategoryResource;

public class UpdateCategoryCommandFromResourceAssembler {
    public static UpdateCategoryCommand toCommandFromResource(UpdateCategoryResource resource) {
        return new UpdateCategoryCommand(resource.name(), resource.description());
    }
}
