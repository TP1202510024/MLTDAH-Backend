package pe.edu.upc.MLTDAH.students.domain.services;

import pe.edu.upc.MLTDAH.students.domain.model.commands.SeedSchoolGradesCommand;

public interface SchoolGradeCommandService {
    void handle(SeedSchoolGradesCommand command);
}
