package pe.edu.upc.MLTDAH.iam.domain.model.commands;

public record SignInCommand(String email, String password) {
    public SignInCommand {

        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("email cannot be null or empty");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("password cannot be null or empty");
        }
    }
}