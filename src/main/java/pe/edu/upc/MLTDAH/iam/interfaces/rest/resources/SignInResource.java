package pe.edu.upc.MLTDAH.iam.interfaces.rest.resources;

public record SignInResource(String email, String password) {
    public SignInResource {
        if(email == null || email.isEmpty()) {
            throw new IllegalArgumentException("email is empty or null");
        }
        if(password == null || password.isEmpty()) {
            throw new IllegalArgumentException("password is empty or null");
        }
    }
}
