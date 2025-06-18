package pe.edu.upc.MLTDAH.tests.interfaces.rest.transform;

import pe.edu.upc.MLTDAH.tests.domain.model.aggregates.Exam;
import pe.edu.upc.MLTDAH.tests.interfaces.rest.resources.ExamResource;

public class ExamResourceFromEntityAssembler {
    public static ExamResource toResourceFromEntity(Exam entity) {
        return new ExamResource(entity.getId(), entity.getTitle(), entity.getDescription(), entity.getQuestions().stream().map(QuestionResourceFromEntityAssembler::toResourceFromEntity).toList(), entity.getCreatedAt(), entity.getUpdatedAt());
    }
}
