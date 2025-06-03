package pe.edu.upc.MLTDAH.iam.domain.model.commands;

public record SignInCommand(String username, String password) {
    public SignInCommand {

        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("username cannot be null or empty");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("password cannot be null or empty");
        }
    }
}