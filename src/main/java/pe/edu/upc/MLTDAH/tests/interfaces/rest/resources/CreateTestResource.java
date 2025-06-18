package pe.edu.upc.MLTDAH.tests.interfaces.rest.resources;

import pe.edu.upc.MLTDAH.tests.domain.model.dto.AnswerDTO;

import java.util.List;

public record CreateTestResource(Long studentId, Long examId, List<AnswerDTO> answers) {
    public CreateTestResource {
        if(studentId == null || studentId < 0) {
            throw new IllegalArgumentException("studentId cannot be null");
        }
        if(examId == null || examId < 0) {
            throw new IllegalArgumentException("examId cannot be null");
        }
        if(answers == null || answers.isEmpty()) {
            throw new IllegalArgumentException("answers cannot be empty");
        }
    }
}
