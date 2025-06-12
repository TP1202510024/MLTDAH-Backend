package pe.edu.upc.MLTDAH.students.interfaces.rest.transform;

import pe.edu.upc.MLTDAH.students.domain.model.entities.SchoolGrade;
import pe.edu.upc.MLTDAH.students.interfaces.rest.resources.SchoolGradeResource;

public class SchoolGradeResourceFromEntityAssembler {
    public static SchoolGradeResource toResourceFromEntity(SchoolGrade entity) {
        return new SchoolGradeResource(entity.getId(), entity.getStringName());
    }
}
