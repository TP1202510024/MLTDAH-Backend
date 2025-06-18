package pe.edu.upc.MLTDAH.students.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.MLTDAH.iam.domain.model.aggregates.Institution;
import pe.edu.upc.MLTDAH.iam.infrastructure.persistence.jpa.InstitutionRepository;
import pe.edu.upc.MLTDAH.students.domain.model.aggregates.Student;
import pe.edu.upc.MLTDAH.students.domain.model.commands.CreateStudentCommand;
import pe.edu.upc.MLTDAH.students.domain.model.commands.DeleteStudentCommand;
import pe.edu.upc.MLTDAH.students.domain.model.commands.UpdateStudentCommand;
import pe.edu.upc.MLTDAH.students.domain.model.commands.UploadStudentImageCommand;
import pe.edu.upc.MLTDAH.students.domain.model.entities.Gender;
import pe.edu.upc.MLTDAH.students.domain.model.entities.SchoolGrade;
import pe.edu.upc.MLTDAH.students.domain.services.StudentCommandService;
import pe.edu.upc.MLTDAH.students.infrastructure.persistence.jpa.GenderRepository;
import pe.edu.upc.MLTDAH.students.infrastructure.persistence.jpa.SchoolGradeRepository;
import pe.edu.upc.MLTDAH.students.infrastructure.persistence.jpa.StudentRepository;
import pe.edu.upc.MLTDAH.uploads.application.internal.outboundservices.S3Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class StudentCommandServiceImplementation implements StudentCommandService {
    private final StudentRepository studentRepository;
    private final GenderRepository genderRepository;
    private final SchoolGradeRepository schoolGradeRepository;
    private final InstitutionRepository institutionRepository;
    private final S3Service s3Service;

    public StudentCommandServiceImplementation(StudentRepository studentRepository, GenderRepository genderRepository, SchoolGradeRepository schoolGradeRepository, InstitutionRepository institutionRepository, S3Service s3Service) {
        this.studentRepository = studentRepository;
        this.genderRepository = genderRepository;
        this.schoolGradeRepository = schoolGradeRepository;
        this.institutionRepository = institutionRepository;
        this.s3Service = s3Service;
    }

    @Override
    public Optional<Student> handle(CreateStudentCommand command) {
        SchoolGrade schoolGrade = this.schoolGradeRepository.findById(command.schoolGradeId()).orElseThrow(() -> new IllegalArgumentException("School Grade not found"));
        Gender gender = this.genderRepository.findById(command.genderId()).orElseThrow(() -> new IllegalArgumentException("Gender not found"));
        Institution institution = this.institutionRepository.findById(command.institutionId()).orElseThrow(() -> new IllegalArgumentException("Institution not found"));

        Student student = new Student(command, schoolGrade, gender, institution);

        var studentSaved = this.studentRepository.save(student);

        return Optional.of(studentSaved);
    }

    @Override
    public Optional<Student> handle(UpdateStudentCommand command, Long id) {
        Student student = this.studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Student not found"));

        SchoolGrade schoolGrade = this.schoolGradeRepository.findById(command.schoolGradeId()).orElseThrow(() -> new IllegalArgumentException("School Grade not found"));
        Gender gender = this.genderRepository.findById(command.genderId()).orElseThrow(() -> new IllegalArgumentException("Gender not found"));

        student.setFirstName(command.firstName());
        student.setLastName(command.lastName());
        student.setBirthDate(command.birthDate());
        student.setPhoto(command.photo());
        student.setSchoolGrade(schoolGrade);
        student.setGender(gender);

        var studentSaved = this.studentRepository.save(student);

        return Optional.of(studentSaved);
    }

    @Override
    public Optional<Student> handle(DeleteStudentCommand command) {
        Student student = this.studentRepository.findById(command.id()).orElseThrow(() -> new IllegalArgumentException("Student not found"));

        this.studentRepository.delete(student);

        return Optional.of(student);
    }

    @Override
    public Optional<Student> handle(UploadStudentImageCommand command, Long id) throws IOException {
        Student student = this.studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Student not found"));

        String userImageUrl = this.s3Service.uploadFile(command.file());
        student.setPhoto(userImageUrl);

        var studentSaved = this.studentRepository.save(student);

        return Optional.of(studentSaved);
    }
}
