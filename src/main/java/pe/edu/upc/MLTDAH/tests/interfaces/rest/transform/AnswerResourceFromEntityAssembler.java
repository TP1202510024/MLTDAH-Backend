package pe.edu.upc.MLTDAH.tests.interfaces.rest.transform;

import pe.edu.upc.MLTDAH.tests.domain.model.entities.Answer;
import pe.edu.upc.MLTDAH.tests.interfaces.rest.resources.AnswerResource;

public class AnswerResourceFromEntityAssembler {
    public static AnswerResource toResourceFromEntity(Answer entity) {
        return new AnswerResource(entity.getId(), entity.getValue(), QuestionResourceFromEntityAssembler.toResourceFromEntity(entity.getQuestion()));
    }
}
