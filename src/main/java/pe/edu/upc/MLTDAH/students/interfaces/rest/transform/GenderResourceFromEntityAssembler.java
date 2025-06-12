package pe.edu.upc.MLTDAH.students.interfaces.rest.transform;

import pe.edu.upc.MLTDAH.students.domain.model.entities.Gender;
import pe.edu.upc.MLTDAH.students.interfaces.rest.resources.GenderResource;

public class GenderResourceFromEntityAssembler {
    public static GenderResource toResourceFromEntity(Gender gender) {
        return new GenderResource(gender.getId(), gender.getStringName());
    }
}
