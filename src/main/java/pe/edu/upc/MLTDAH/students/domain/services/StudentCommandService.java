package pe.edu.upc.MLTDAH.students.domain.services;

import pe.edu.upc.MLTDAH.students.domain.model.aggregates.Student;
import pe.edu.upc.MLTDAH.students.domain.model.commands.CreateStudentCommand;
import pe.edu.upc.MLTDAH.students.domain.model.commands.DeleteStudentCommand;
import pe.edu.upc.MLTDAH.students.domain.model.commands.UpdateStudentCommand;

import java.util.Optional;

public interface StudentCommandService {
    Optional<Student> handle(CreateStudentCommand command);
    Optional<Student> handle(UpdateStudentCommand command, Long id);
    Optional<Student> handle(DeleteStudentCommand command);
}
