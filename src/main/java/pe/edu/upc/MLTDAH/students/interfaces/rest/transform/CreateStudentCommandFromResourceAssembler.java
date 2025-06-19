package pe.edu.upc.MLTDAH.students.interfaces.rest.transform;

import pe.edu.upc.MLTDAH.students.domain.model.commands.CreateStudentCommand;
import pe.edu.upc.MLTDAH.students.interfaces.rest.resources.CreateStudentResource;

public class CreateStudentCommandFromResourceAssembler {
    public static CreateStudentCommand toCommandFromResource(CreateStudentResource resource) {
        return new CreateStudentCommand(resource.firstName(), resource.lastName(), resource.birthDate(), resource.schoolGradeId(), resource.genderId(), resource.institutionId());
    }
}
