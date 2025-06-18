package pe.edu.upc.MLTDAH.tests.domain.model.commands;

public record UpdateQuestionCommand(String text, Long categoryId) {
    public UpdateQuestionCommand {
        if(text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Text cannot be null or empty");
        }
        if(categoryId == null || categoryId < 0) {
            throw new IllegalArgumentException("CategoryId cannot be null or negative");
        }
    }
}