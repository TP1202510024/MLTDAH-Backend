package pe.edu.upc.MLTDAH.tests.interfaces.rest.resources;

import java.util.Date;

public record QuestionResource(Long id, String text, CategoryResource category, Date createdAt, Date updatedAt) {
}
