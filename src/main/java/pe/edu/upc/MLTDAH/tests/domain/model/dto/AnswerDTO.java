package pe.edu.upc.MLTDAH.tests.domain.model.dto;

public record AnswerDTO(String value, Long questionId) {
    public AnswerDTO {
        if(value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Answer value cannot be null or empty");
        }
        if(questionId == null || questionId < 0) {
            throw new IllegalArgumentException("Question ID cannot be null or negative");
        }
    }
}
