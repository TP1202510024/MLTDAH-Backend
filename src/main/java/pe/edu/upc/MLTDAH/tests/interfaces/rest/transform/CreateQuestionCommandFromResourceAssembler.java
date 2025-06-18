package pe.edu.upc.MLTDAH.tests.interfaces.rest.transform;

import pe.edu.upc.MLTDAH.tests.domain.model.commands.CreateQuestionCommand;
import pe.edu.upc.MLTDAH.tests.interfaces.rest.resources.CreateQuestionResource;

public class CreateQuestionCommandFromResourceAssembler {
    public static CreateQuestionCommand toCommandFromResource(CreateQuestionResource resource) {
        return new CreateQuestionCommand(resource.text(), resource.categoryId(), resource.examId());
    }
}
