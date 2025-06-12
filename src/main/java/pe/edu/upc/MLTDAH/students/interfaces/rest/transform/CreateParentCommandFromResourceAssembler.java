package pe.edu.upc.MLTDAH.students.interfaces.rest.transform;

import pe.edu.upc.MLTDAH.students.domain.model.commands.CreateParentCommand;
import pe.edu.upc.MLTDAH.students.interfaces.rest.resources.CreateParentResource;

public class CreateParentCommandFromResourceAssembler {
    public static CreateParentCommand toCommandFromResource(CreateParentResource resource) {
        return  new CreateParentCommand(resource.studentId(), resource.parentId());
    }
}
