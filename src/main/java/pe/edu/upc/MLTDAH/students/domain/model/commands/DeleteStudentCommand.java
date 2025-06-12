package pe.edu.upc.MLTDAH.students.domain.model.commands;

public record DeleteStudentCommand(Long id) {
    public DeleteStudentCommand {
        if(id == null) {
            throw  new IllegalArgumentException("student id cannot be null");
        }
    }
}
