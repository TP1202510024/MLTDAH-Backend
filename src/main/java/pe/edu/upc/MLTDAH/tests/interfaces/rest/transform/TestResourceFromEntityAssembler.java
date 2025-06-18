package pe.edu.upc.MLTDAH.tests.interfaces.rest.transform;

import pe.edu.upc.MLTDAH.students.interfaces.rest.transform.StudentResourceFromEntityAssembler;
import pe.edu.upc.MLTDAH.tests.domain.model.aggregates.Test;
import pe.edu.upc.MLTDAH.tests.interfaces.rest.resources.TestResource;

public class TestResourceFromEntityAssembler {
    public static TestResource toResourceFromEntity(Test entity) {
        return new TestResource(entity.getId(), StudentResourceFromEntityAssembler.toResourceFromEntity(entity.getStudent()), ExamResourceFromEntityAssembler.toResourceFromEntity(entity.getExam()), entity.getAnswers().stream().map(AnswerResourceFromEntityAssembler::toResourceFromEntity).toList(), entity.getProbability(), entity.getResult(), entity.getCreatedAt(), entity.getUpdatedAt());
    }
}
