package pe.edu.upc.MLTDAH.iam.interfaces.rest.resources;

public record RoleResource(Long id, String name) {
    public RoleResource {
        if (id == null) {
            throw new NullPointerException("id is null");
        }
        if (name == null) {
            throw new NullPointerException("name is null");
        }
    }
}
