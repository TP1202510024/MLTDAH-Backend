package pe.edu.upc.MLTDAH.students.domain.model.commands;

import java.util.Date;

public record UpdateStudentCommand(String firstName, String lastName, Date birthDate, String photo, String schoolGradeId, String genderId) {
    public UpdateStudentCommand {
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
        if (schoolGradeId == null || schoolGradeId.isBlank()) {
            throw new IllegalArgumentException("school grade id cannot be null or empty");
        }
        if (genderId == null || genderId.isBlank()) {
            throw new IllegalArgumentException("gender id cannot be null or empty");
        }
    }
}