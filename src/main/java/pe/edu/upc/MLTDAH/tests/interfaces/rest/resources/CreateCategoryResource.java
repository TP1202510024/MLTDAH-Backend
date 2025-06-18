package pe.edu.upc.MLTDAH.tests.interfaces.rest.resources;

public record CreateCategoryResource(String name, String description) {
    public CreateCategoryResource {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
    }
}
