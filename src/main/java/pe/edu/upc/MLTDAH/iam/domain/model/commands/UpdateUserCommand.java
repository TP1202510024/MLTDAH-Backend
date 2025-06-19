package pe.edu.upc.MLTDAH.iam.domain.model.commands;

import pe.edu.upc.MLTDAH.iam.domain.model.valueobjects.Roles;

import java.util.Date;

public record UpdateUserCommand(String firstName, String lastName, String dni, Date birthDate) {
    public UpdateUserCommand {
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("firstName cannot be null or empty");
        }
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("lastName cannot be null or empty");
        }
        if (dni == null || dni.isBlank()) {
            throw new IllegalArgumentException("dni cannot be null");
        }
        if (birthDate == null) {
            throw new IllegalArgumentException("birthDate cannot be null");
        }
    }
}
