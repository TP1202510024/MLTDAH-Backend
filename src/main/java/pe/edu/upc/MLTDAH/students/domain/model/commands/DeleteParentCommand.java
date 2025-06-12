package pe.edu.upc.MLTDAH.students.domain.model.commands;

public record DeleteParentCommand(Long studentId) {
    public DeleteParentCommand {
        if (studentId == null || studentId <= 0) {
            throw new IllegalArgumentException("studentId cannot be null or zero or greater than 0");
        }
    }
}
