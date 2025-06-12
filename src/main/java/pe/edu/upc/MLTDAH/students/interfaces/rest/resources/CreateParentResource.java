package pe.edu.upc.MLTDAH.students.interfaces.rest.resources;

public record CreateParentResource(Long studentId, Long parentId){
    public CreateParentResource {
        if(studentId == null || studentId <= 0) {
            throw new IllegalArgumentException("studentId must be greater than 0");
        }
        if(parentId == null || parentId <= 0) {
            throw new IllegalArgumentException("parentId must be greater than 0");
        }
    }
}
