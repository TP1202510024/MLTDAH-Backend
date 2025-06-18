package pe.edu.upc.MLTDAH.tests.interfaces.rest.resources;

import java.util.Date;

public record CategoryResource(Long id, String name, String description, Date createdAt, Date updatedAt) {
}
