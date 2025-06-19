package pe.edu.upc.MLTDAH.iam.domain.model.commands;

public record UpdatePasswordCommand(String password, String repeatedPassword, String restartCode) {
    public UpdatePasswordCommand {
        if(password == null || password.isEmpty()) {
            throw new IllegalArgumentException("password cannot be null or empty");
        }
        if(repeatedPassword == null || repeatedPassword.isEmpty()) {
            throw new IllegalArgumentException("repeat password cannot be null or empty");
        }
        if(restartCode == null || restartCode.isEmpty()) {
            throw new IllegalArgumentException("restart code cannot be null or empty");
        }
    }
}
