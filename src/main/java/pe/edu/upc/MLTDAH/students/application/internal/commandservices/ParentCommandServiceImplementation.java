package pe.edu.upc.MLTDAH.students.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.MLTDAH.iam.domain.model.aggregates.User;
import pe.edu.upc.MLTDAH.iam.infrastructure.persistence.jpa.UserRepository;
import pe.edu.upc.MLTDAH.students.domain.model.aggregates.Parent;
import pe.edu.upc.MLTDAH.students.domain.model.aggregates.Student;
import pe.edu.upc.MLTDAH.students.domain.model.commands.CreateParentCommand;
import pe.edu.upc.MLTDAH.students.domain.model.commands.DeleteParentCommand;
import pe.edu.upc.MLTDAH.students.domain.services.ParentCommandService;
import pe.edu.upc.MLTDAH.students.infrastructure.persistence.jpa.ParentRepository;
import pe.edu.upc.MLTDAH.students.infrastructure.persistence.jpa.StudentRepository;

import java.util.Optional;

@Service
public class ParentCommandServiceImplementation implements ParentCommandService {
    private final ParentRepository parentRepository;
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    public ParentCommandServiceImplementation(ParentRepository parentRepository, StudentRepository studentRepository, UserRepository userRepository) {
        this.parentRepository = parentRepository;
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<Parent> handle(CreateParentCommand command) {
        User user = userRepository.findById(command.parentId()).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Student student = studentRepository.findById(command.studentId()).orElseThrow(() -> new IllegalArgumentException("Student not found"));

        Parent parent = new Parent(user, student);

        var parentSaved = parentRepository.save(parent);

        return Optional.of(parentSaved);
    }

    @Override
    public Optional<Parent> handle(DeleteParentCommand command) {
        Student student = studentRepository.findById(command.studentId()).orElseThrow(() -> new IllegalArgumentException("Student not found"));
        Parent parent = parentRepository.findByStudentId(student.getId()).orElseThrow(() -> new IllegalArgumentException("Parent not found"));

        parentRepository.delete(parent);

        return Optional.of(parent);
    }
}
