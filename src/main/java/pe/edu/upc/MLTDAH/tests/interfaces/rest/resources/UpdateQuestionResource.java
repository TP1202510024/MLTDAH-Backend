package pe.edu.upc.MLTDAH.tests.interfaces.rest.resources;

public record UpdateQuestionResource(String text, Long categoryId) {
    public UpdateQuestionResource {
        if(text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Text cannot be null or empty");
        }
        if(categoryId == null || categoryId < 0) {
            throw new IllegalArgumentException("CategoryId cannot be null or negative");
        }
    }
}
