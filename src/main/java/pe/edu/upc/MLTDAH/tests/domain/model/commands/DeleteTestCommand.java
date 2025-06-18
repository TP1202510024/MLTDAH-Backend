package pe.edu.upc.MLTDAH.tests.domain.model.commands;

public record DeleteTestCommand(Long id) {
    public DeleteTestCommand {
        if(id == null || id < 0) {
            throw new IllegalArgumentException("id is invalid");
        }
    }
}
