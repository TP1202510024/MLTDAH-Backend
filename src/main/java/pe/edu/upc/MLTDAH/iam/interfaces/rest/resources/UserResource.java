package pe.edu.upc.MLTDAH.iam.interfaces.rest.resources;

import java.util.Date;

public record UserResource(Long id, String firstName, String lastName, String dni, Date birthDate, String photo, String email, InstitutionResource institution, RoleResource role) {
    public UserResource {
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
        if (photo == null || photo.isBlank()) {
            throw new IllegalArgumentException("photo user cannot be null");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("email cannot be null or empty");
        }
        if (institution == null) {
            throw new IllegalArgumentException("institution cannot be null");
        }
        if(role == null){
            throw new IllegalArgumentException("roleResource cannot be null");
        }
    }
}
