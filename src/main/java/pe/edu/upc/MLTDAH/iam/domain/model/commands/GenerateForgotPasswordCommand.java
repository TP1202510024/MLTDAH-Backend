package pe.edu.upc.MLTDAH.iam.domain.model.commands;

public record GenerateForgotPasswordCommand(String email) {
    public GenerateForgotPasswordCommand {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
    }
}
