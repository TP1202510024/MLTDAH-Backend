package pe.edu.upc.MLTDAH.students.interfaces.rest.transform;

import pe.edu.upc.MLTDAH.students.domain.model.commands.UpdateStudentCommand;
import pe.edu.upc.MLTDAH.students.interfaces.rest.resources.UpdateStudentResource;

public class UpdateStudentCommandFromResourceAssembler {
    public static UpdateStudentCommand toCommandFromResource(UpdateStudentResource resource) {
        return new UpdateStudentCommand(resource.firstName(), resource.lastName(), resource.birthDate(), resource.photo(), resource.schoolGradeId(), resource.genderId());
    }
}
