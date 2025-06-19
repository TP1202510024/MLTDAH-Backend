package pe.edu.upc.MLTDAH.iam.interfaces.rest.resources;

public record GenerateForgotPasswordResource(String email) {
    public GenerateForgotPasswordResource {
        if(email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
    }
}
