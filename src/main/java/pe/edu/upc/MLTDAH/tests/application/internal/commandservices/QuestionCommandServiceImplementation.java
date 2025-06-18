package pe.edu.upc.MLTDAH.tests.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.MLTDAH.tests.domain.model.aggregates.Exam;
import pe.edu.upc.MLTDAH.tests.domain.model.commands.CreateQuestionCommand;
import pe.edu.upc.MLTDAH.tests.domain.model.commands.DeleteQuestionCommand;
import pe.edu.upc.MLTDAH.tests.domain.model.commands.UpdateQuestionCommand;
import pe.edu.upc.MLTDAH.tests.domain.model.entities.Category;
import pe.edu.upc.MLTDAH.tests.domain.model.entities.Question;
import pe.edu.upc.MLTDAH.tests.domain.services.QuestionCommandService;
import pe.edu.upc.MLTDAH.tests.infrastructure.persistence.jpa.CategoryRepository;
import pe.edu.upc.MLTDAH.tests.infrastructure.persistence.jpa.ExamRepository;
import pe.edu.upc.MLTDAH.tests.infrastructure.persistence.jpa.QuestionRepository;

import java.util.Optional;

@Service
public class QuestionCommandServiceImplementation implements QuestionCommandService {
    private final QuestionRepository questionRepository;
    private final CategoryRepository categoryRepository;
    private final ExamRepository examRepository;

    public QuestionCommandServiceImplementation(QuestionRepository questionRepository, CategoryRepository categoryRepository, ExamRepository examRepository) {
        this.questionRepository = questionRepository;
        this.categoryRepository = categoryRepository;
        this.examRepository = examRepository;
    }

    @Override
    public Optional<Question> handle(CreateQuestionCommand command) {
        Exam exam = this.examRepository.findById(command.examId()).orElseThrow(() -> new IllegalArgumentException("Exam not found"));
        Category category = this.categoryRepository.findById(command.categoryId()).orElseThrow(() -> new IllegalArgumentException("Category not found"));

        Question question = new Question(command, category, exam);

        var questionSaved = questionRepository.save(question);

        return Optional.of(questionSaved);
    }

    @Override
    public Optional<Question> handle(UpdateQuestionCommand command, Long id) {
        Question question = this.questionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Question not found"));
        Category category = this.categoryRepository.findById(command.categoryId()).orElseThrow(() -> new IllegalArgumentException("Category not found"));

        question.setCategory(category);
        question.setText(command.text());

        var questionSaved = questionRepository.save(question);

        return Optional.of(questionSaved);
    }

    @Override
    public Optional<Question> handle(DeleteQuestionCommand command) {
        Question question = this.questionRepository.findById(command.id()).orElseThrow(() -> new IllegalArgumentException("Question not found"));

        this.questionRepository.delete(question);

        return Optional.of(question);
    }
}
