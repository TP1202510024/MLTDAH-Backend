package pe.edu.upc.MLTDAH.iam.interfaces.rest.resources;

public record UpdatePasswordResource(String password, String repeatedPassword, String restartCode) {
    public UpdatePasswordResource {
        if(password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        if(repeatedPassword == null || repeatedPassword.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        if(restartCode == null || restartCode.isEmpty()) {
            throw new IllegalArgumentException("RestartCode cannot be null or empty");
        }
    }
}
