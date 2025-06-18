package pe.edu.upc.MLTDAH.tests.domain.model.commands;

public record DeleteExamCommand(Long id) {
    public DeleteExamCommand {
        if(id == null || id < 0) {
            throw new IllegalArgumentException("id is invalid");
        }
    }
}
