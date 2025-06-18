package pe.edu.upc.MLTDAH.tests.interfaces.rest.transform;

import pe.edu.upc.MLTDAH.tests.domain.model.entities.Question;
import pe.edu.upc.MLTDAH.tests.interfaces.rest.resources.QuestionResource;

public class QuestionResourceFromEntityAssembler {
    public static QuestionResource toResourceFromEntity(Question entity) {
        return new QuestionResource(entity.getId(), entity.getText(), CategoryResourceFromEntityAssembler.toResourceFromEntity(entity.getCategory()), entity.getCreatedAt(), entity.getUpdatedAt());
    }
}
