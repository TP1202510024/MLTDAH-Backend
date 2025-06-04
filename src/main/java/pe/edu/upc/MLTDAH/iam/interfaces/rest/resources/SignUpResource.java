package pe.edu.upc.MLTDAH.iam.interfaces.rest.resources;

import java.util.Date;

public record SignUpResource(String firstName, String lastName, String dni, Date birthDate, String photoUser, String email, String password, Long institutionId) {
    public SignUpResource {
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
        if (photoUser == null || photoUser.isBlank()) {
            throw new IllegalArgumentException("photo user cannot be null");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("email cannot be null or empty");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("password cannot be null or empty");
        }
        if (institutionId == null || institutionId <= 0) {
            throw new IllegalArgumentException("institution id must be a positive integer");
        }
    }
}
