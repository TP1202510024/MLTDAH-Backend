package pe.edu.upc.MLTDAH.iam.domain.model.commands;

public record DeleteUserCommand(Long id) {
    public DeleteUserCommand {
        if(id == null) {
            throw  new IllegalArgumentException("user id cannot be null");
        }
    }
}
