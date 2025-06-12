package pe.edu.upc.MLTDAH.students.interfaces.rest.resources;

import pe.edu.upc.MLTDAH.iam.interfaces.rest.resources.InstitutionResource;

import java.util.Date;

public record StudentResource(Long id, String firstName, String lastName, Date birthDate, String photo, SchoolGradeResource schoolGrade, GenderResource gender, InstitutionResource institution) {
}
