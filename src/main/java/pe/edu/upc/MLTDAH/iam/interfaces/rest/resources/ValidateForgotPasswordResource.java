package pe.edu.upc.MLTDAH.iam.interfaces.rest.resources;

public record ValidateForgotPasswordResource(String restartCode) {
    public ValidateForgotPasswordResource {
        if(restartCode == null || restartCode.isEmpty()) {
            throw new IllegalArgumentException("restartCode cannot be null or empty");
        }
    }
}
