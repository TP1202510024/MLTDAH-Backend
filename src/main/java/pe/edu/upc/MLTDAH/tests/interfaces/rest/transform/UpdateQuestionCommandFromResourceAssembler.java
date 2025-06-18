package pe.edu.upc.MLTDAH.tests.interfaces.rest.transform;

import pe.edu.upc.MLTDAH.tests.domain.model.commands.UpdateQuestionCommand;
import pe.edu.upc.MLTDAH.tests.interfaces.rest.resources.UpdateQuestionResource;

public class UpdateQuestionCommandFromResourceAssembler {
    public static UpdateQuestionCommand toCommandFromResource(UpdateQuestionResource resource) {
        return new UpdateQuestionCommand(resource.text(), resource.categoryId());
    }
}
