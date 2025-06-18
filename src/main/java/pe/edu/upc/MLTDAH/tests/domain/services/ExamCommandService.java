package pe.edu.upc.MLTDAH.tests.domain.services;

import pe.edu.upc.MLTDAH.tests.domain.model.aggregates.Exam;
import pe.edu.upc.MLTDAH.tests.domain.model.commands.CreateExamCommand;
import pe.edu.upc.MLTDAH.tests.domain.model.commands.DeleteExamCommand;
import pe.edu.upc.MLTDAH.tests.domain.model.commands.UpdateExamCommand;

import java.util.Optional;

public interface ExamCommandService {
    Optional<Exam> handle(CreateExamCommand command);
    Optional<Exam> handle(UpdateExamCommand command, Long id);
    Optional<Exam> handle(DeleteExamCommand command);
}
