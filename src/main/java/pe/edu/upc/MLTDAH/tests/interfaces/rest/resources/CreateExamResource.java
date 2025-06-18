package pe.edu.upc.MLTDAH.tests.interfaces.rest.resources;

public record CreateExamResource(String title, String description) {
    public  CreateExamResource {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("title cannot be null or empty");
        }
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("description cannot be null or empty");
        }
    }
}
