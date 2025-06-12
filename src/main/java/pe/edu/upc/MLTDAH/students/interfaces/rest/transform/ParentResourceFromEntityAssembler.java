package pe.edu.upc.MLTDAH.students.interfaces.rest.transform;

import pe.edu.upc.MLTDAH.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
import pe.edu.upc.MLTDAH.students.domain.model.aggregates.Parent;
import pe.edu.upc.MLTDAH.students.interfaces.rest.resources.ParentResource;

public class ParentResourceFromEntityAssembler {
    public static ParentResource toResourceFromEntity(Parent parent) {
        return new ParentResource(parent.getId(), StudentResourceFromEntityAssembler.toResourceFromEntity(parent.getStudent()), UserResourceFromEntityAssembler.toResourceFromEntity(parent.getParent()));
    }
}
