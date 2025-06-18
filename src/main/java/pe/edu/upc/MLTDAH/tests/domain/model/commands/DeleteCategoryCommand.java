package pe.edu.upc.MLTDAH.tests.domain.model.commands;

public record DeleteCategoryCommand(Long id) {
    public DeleteCategoryCommand {
        if(id == null || id < 0) {
            throw new IllegalArgumentException("id is invalid");
        }
    }
}
