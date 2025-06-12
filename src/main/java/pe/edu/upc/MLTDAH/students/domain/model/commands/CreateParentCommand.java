package pe.edu.upc.MLTDAH.students.domain.model.commands;

public record CreateParentCommand(Long studentId, Long parentId) {
    public CreateParentCommand {
        if (studentId == null || studentId <= 0) {
            throw new IllegalArgumentException("parent id must be a positive integer");
        }
        if (parentId == null || parentId <= 0) {
            throw new IllegalArgumentException("student id must be a positive integer");
        }
    }
}
