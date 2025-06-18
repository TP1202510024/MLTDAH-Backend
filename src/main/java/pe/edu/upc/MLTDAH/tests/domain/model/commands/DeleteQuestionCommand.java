package pe.edu.upc.MLTDAH.tests.domain.model.commands;

public record DeleteQuestionCommand(Long id) {
    public DeleteQuestionCommand {
        if(id == null || id < 0) {
            throw new IllegalArgumentException("id is invalid");
        }
    }
}
