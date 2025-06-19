package pe.edu.upc.MLTDAH.iam.interfaces.rest.resources;

import java.util.Date;

public record SignUpResource(String firstName, String lastName, String dni, Date birthDate, String email, String password, String name, Date creationDate, String address, String photoInstitution) {
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

        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("email cannot be null or empty");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("password cannot be null or empty");
        }
        if(name == null) {
            throw new IllegalArgumentException("name institution is null");
        }
        if(creationDate == null) {
            throw new IllegalArgumentException("creationDate institution is null");
        }
        if(address == null) {
            throw new IllegalArgumentException("address institution is null");
        }
        if(photoInstitution == null) {
            throw new IllegalArgumentException("photo institution is null");
        }
    }
}
