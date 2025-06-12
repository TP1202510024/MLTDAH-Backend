package pe.edu.upc.MLTDAH.students.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.MLTDAH.students.domain.model.commands.SeedSchoolGradesCommand;
import pe.edu.upc.MLTDAH.students.domain.model.entities.SchoolGrade;
import pe.edu.upc.MLTDAH.students.domain.model.valueobjects.SchoolGrades;
import pe.edu.upc.MLTDAH.students.domain.services.SchoolGradeCommandService;
import pe.edu.upc.MLTDAH.students.infrastructure.persistence.jpa.SchoolGradeRepository;

import java.util.Arrays;

@Service
public class SchoolGradeCommandImplementation implements SchoolGradeCommandService {
    private final SchoolGradeRepository schoolGradeRepository;

    public SchoolGradeCommandImplementation(SchoolGradeRepository schoolGradeRepository) {
        this.schoolGradeRepository = schoolGradeRepository;
    }
    @Override
    public void handle(SeedSchoolGradesCommand command) {
        Arrays.stream(SchoolGrades.values()).forEach(schoolGrade -> {
            if(!schoolGradeRepository.existsByName(schoolGrade)) {
                schoolGradeRepository.save(new SchoolGrade(schoolGrade));
            }
        });
    }
}
