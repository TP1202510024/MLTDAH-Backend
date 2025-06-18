package pe.edu.upc.MLTDAH.tests.interfaces.rest.resources;

public record CreateQuestionResource(String text, Long categoryId, Long examId) {
    public CreateQuestionResource {
        if(text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Text cannot be null or empty");
        }
        if(categoryId == null || categoryId < 0) {
            throw new IllegalArgumentException("CategoryId cannot be null or negative");
        }
        if(examId == null || examId < 0) {
            throw new IllegalArgumentException("ExamId cannot be null or negative");
        }
    }
}
