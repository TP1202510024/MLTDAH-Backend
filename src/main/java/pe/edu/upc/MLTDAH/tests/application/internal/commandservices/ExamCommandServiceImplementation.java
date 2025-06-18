package pe.edu.upc.MLTDAH.tests.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.MLTDAH.tests.domain.model.aggregates.Exam;
import pe.edu.upc.MLTDAH.tests.domain.model.commands.CreateExamCommand;
import pe.edu.upc.MLTDAH.tests.domain.model.commands.DeleteExamCommand;
import pe.edu.upc.MLTDAH.tests.domain.model.commands.UpdateExamCommand;
import pe.edu.upc.MLTDAH.tests.domain.model.entities.Category;
import pe.edu.upc.MLTDAH.tests.domain.services.ExamCommandService;
import pe.edu.upc.MLTDAH.tests.infrastructure.persistence.jpa.ExamRepository;

import java.util.Optional;

@Service
public class ExamCommandServiceImplementation implements ExamCommandService {
    private final ExamRepository examRepository;

    public ExamCommandServiceImplementation(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    @Override
    public Optional<Exam> handle(CreateExamCommand command) {
        Exam exam = new Exam(command);

        var examSaved = examRepository.save(exam);

        return Optional.of(examSaved);
    }

    @Override
    public Optional<Exam> handle(UpdateExamCommand command, Long id) {
        Exam exam = examRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Exam not found"));

        exam.setTitle(command.title());
        exam.setDescription(command.description());

        var examSaved = examRepository.save(exam);

        return Optional.of(examSaved);
    }

    @Override
    public Optional<Exam> handle(DeleteExamCommand command) {
        Exam exam = examRepository.findById(command.id()).orElseThrow(() -> new IllegalArgumentException("Exam not found"));

        examRepository.delete(exam);

        return Optional.of(exam);
    }
}
