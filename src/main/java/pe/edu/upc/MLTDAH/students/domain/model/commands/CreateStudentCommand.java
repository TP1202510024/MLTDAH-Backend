package pe.edu.upc.MLTDAH.students.domain.model.commands;

import java.util.Date;

public record CreateStudentCommand(String firstName, String lastName, Date birthDate, String photo, Long schoolGradeId, Long genderId, Long institutionId) {
    public CreateStudentCommand {
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("firstName cannot be null or empty");
        }
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("lastName cannot be null or empty");
        }
        if (birthDate == null) {
            throw new IllegalArgumentException("birthDate cannot be null");
        }
        if (photo == null || photo.isBlank()) {
            throw new IllegalArgumentException("photo cannot be null");
        }
        if (schoolGradeId == null || schoolGradeId <= 0) {
            throw new IllegalArgumentException("school grade id cannot be null");
        }
        if (genderId == null || genderId <= 0) {
            throw new IllegalArgumentException("gender id cannot be null");
        }
        if (institutionId == null || institutionId <= 0) {
            throw new IllegalArgumentException("institution id must be a positive integer");
        }
    }
}
