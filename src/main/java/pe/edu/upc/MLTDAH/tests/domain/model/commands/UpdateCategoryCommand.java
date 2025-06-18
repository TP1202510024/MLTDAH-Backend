package pe.edu.upc.MLTDAH.tests.domain.model.commands;

public record UpdateCategoryCommand(String name, String description) {
    public UpdateCategoryCommand {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
    }
}
