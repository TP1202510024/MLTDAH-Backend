package pe.edu.upc.MLTDAH.tests.interfaces.rest.resources;

import java.util.Date;
import java.util.List;

public record ExamResource(Long id, String title, String description, List<QuestionResource> questions, Date createdAt, Date updatedAt) {
}
