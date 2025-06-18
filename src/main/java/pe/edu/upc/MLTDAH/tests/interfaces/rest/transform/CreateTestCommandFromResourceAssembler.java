package pe.edu.upc.MLTDAH.tests.interfaces.rest.transform;

import pe.edu.upc.MLTDAH.tests.domain.model.commands.CreateTestCommand;
import pe.edu.upc.MLTDAH.tests.interfaces.rest.resources.CreateTestResource;

public class CreateTestCommandFromResourceAssembler {
    public static CreateTestCommand toCommandFromResource(CreateTestResource resource) {
        return new CreateTestCommand(resource.studentId(), resource.examId(), resource.answers());
    }
}
