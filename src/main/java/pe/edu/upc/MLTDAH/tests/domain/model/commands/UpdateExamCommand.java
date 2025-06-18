package pe.edu.upc.MLTDAH.tests.domain.model.commands;

public record UpdateExamCommand(String title, String description) {
    public  UpdateExamCommand {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("title cannot be null or empty");
        }
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("description cannot be null or empty");
        }
    }
}
