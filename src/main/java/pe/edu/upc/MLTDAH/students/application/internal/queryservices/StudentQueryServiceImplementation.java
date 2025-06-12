package pe.edu.upc.MLTDAH.students.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.MLTDAH.students.domain.model.aggregates.Student;
import pe.edu.upc.MLTDAH.students.domain.model.queries.*;
import pe.edu.upc.MLTDAH.students.domain.services.StudentQueryService;
import pe.edu.upc.MLTDAH.students.infrastructure.persistence.jpa.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentQueryServiceImplementation implements StudentQueryService {
    private final StudentRepository studentRepository;

    public StudentQueryServiceImplementation(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> handle(GetAllStudentsQuery query) {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> handle(GetAllStudentsByInstitutionIdQuery query) {
        return studentRepository.findByInstitutionId(query.institutionId());
    }

    @Override
    public List<Student> handle(GetAllStudentsByInstitutionIdAndGradeIdQuery query) {
        return studentRepository.findByInstitutionIdAndSchoolGradeId(query.institutionId(), query.gradeId());
    }

    @Override
    public List<Student> handle(GetAllStudentsByInstitutionIdAndGenderIdQuery query) {
        return studentRepository.findByInstitutionIdAndGenderId(query.institutionId(), query.genderId());
    }

    @Override
    public List<Student> handle(GetAllStudentsByInstitutionIdAndGenderIdAndGradeIdQuery query) {
        return studentRepository.findByInstitutionIdAndGenderIdAndSchoolGradeId(query.institutionId(), query.genderId(), query.gradeId());
    }

    @Override
    public Optional<Student> handle(GetStudentByIdQuery query) {
        return studentRepository.findById(query.id());
    }
}
