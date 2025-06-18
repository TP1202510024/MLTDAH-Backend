package pe.edu.upc.MLTDAH.tests.interfaces.rest.resources;

import pe.edu.upc.MLTDAH.students.interfaces.rest.resources.StudentResource;

import java.util.Date;
import java.util.List;

public record TestResource(Long id, StudentResource student, ExamResource exam, List<AnswerResource> answers, Double probability, String result, Date createdAt, Date updatedAt) {
}
