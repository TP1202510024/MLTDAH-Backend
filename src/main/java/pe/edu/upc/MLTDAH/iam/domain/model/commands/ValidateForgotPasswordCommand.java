package pe.edu.upc.MLTDAH.iam.domain.model.commands;

public record ValidateForgotPasswordCommand(String restartCode) {
    public ValidateForgotPasswordCommand {
        if(restartCode == null || restartCode.isEmpty()) {
            throw new IllegalArgumentException("restartCode cannot be null or empty");
        }
    }
}
