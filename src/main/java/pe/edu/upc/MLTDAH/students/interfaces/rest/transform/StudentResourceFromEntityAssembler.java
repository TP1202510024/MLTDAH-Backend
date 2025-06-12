package pe.edu.upc.MLTDAH.students.interfaces.rest.transform;

import pe.edu.upc.MLTDAH.iam.interfaces.rest.transform.InstitutionResourceFromEntityAssembler;
import pe.edu.upc.MLTDAH.students.domain.model.aggregates.Student;
import pe.edu.upc.MLTDAH.students.interfaces.rest.resources.StudentResource;

public class StudentResourceFromEntityAssembler {
    public static StudentResource toResourceFromEntity(Student student) {
        return new StudentResource(student.getId(), student.getFirstName(), student.getLastName(), student.getBirthDate(), student.getPhoto(), SchoolGradeResourceFromEntityAssembler.toResourceFromEntity(student.getSchoolGrade()), GenderResourceFromEntityAssembler.toResourceFromEntity(student.getGender()), InstitutionResourceFromEntityAssembler.toResourceFromEntity(student.getInstitution()));
    }
}
