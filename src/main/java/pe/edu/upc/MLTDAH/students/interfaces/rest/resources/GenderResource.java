package pe.edu.upc.MLTDAH.students.interfaces.rest.resources;

public record GenderResource(Long id, String name) {
    public GenderResource {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        if (name == null) {
            throw new IllegalArgumentException("name is null");
        }
    }
}
