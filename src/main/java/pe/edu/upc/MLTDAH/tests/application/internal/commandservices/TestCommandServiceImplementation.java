package pe.edu.upc.MLTDAH.tests.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.MLTDAH.students.domain.model.aggregates.Student;
import pe.edu.upc.MLTDAH.students.infrastructure.persistence.jpa.StudentRepository;
import pe.edu.upc.MLTDAH.tests.application.internal.outboundservices.MLService;
import pe.edu.upc.MLTDAH.tests.domain.model.aggregates.Exam;
import pe.edu.upc.MLTDAH.tests.domain.model.aggregates.Test;
import pe.edu.upc.MLTDAH.tests.domain.model.commands.CreateTestCommand;
import pe.edu.upc.MLTDAH.tests.domain.model.commands.DeleteTestCommand;
import pe.edu.upc.MLTDAH.tests.domain.model.dto.AnswerDTO;
import pe.edu.upc.MLTDAH.tests.domain.model.dto.MLResultDTO;
import pe.edu.upc.MLTDAH.tests.domain.model.entities.Answer;
import pe.edu.upc.MLTDAH.tests.domain.model.entities.Question;
import pe.edu.upc.MLTDAH.tests.domain.services.TestCommandService;
import pe.edu.upc.MLTDAH.tests.infrastructure.persistence.jpa.AnswerRepository;
import pe.edu.upc.MLTDAH.tests.infrastructure.persistence.jpa.ExamRepository;
import pe.edu.upc.MLTDAH.tests.infrastructure.persistence.jpa.QuestionRepository;
import pe.edu.upc.MLTDAH.tests.infrastructure.persistence.jpa.TestRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TestCommandServiceImplementation implements TestCommandService {
    private final TestRepository testRepository;
    private final StudentRepository studentRepository;
    private final ExamRepository examRepository;
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final MLService mlService;

    public TestCommandServiceImplementation(TestRepository testRepository, StudentRepository studentRepository, ExamRepository examRepository, AnswerRepository answerRepository, QuestionRepository questionRepository, MLService mlService) {
        this.testRepository = testRepository;
        this.studentRepository = studentRepository;
        this.examRepository = examRepository;
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
        this.mlService = mlService;
    }

    @Override
    public Optional<Test> handle(CreateTestCommand command) {
        Exam exam = this.examRepository.findById(command.examId()).orElseThrow(() -> new IllegalArgumentException("Exam not found"));
        Student student = this.studentRepository.findById(command.studentId()).orElseThrow(() -> new IllegalArgumentException("Student not found"));

        Test test = new Test(command, student, exam);

        MLResultDTO mlResultDTO = this.mlService.sendTest(command.answers());

        test.setResult(mlResultDTO.result());
        test.setProbability(mlResultDTO.probability());

        var testSaved = this.testRepository.save(test);

        List<Answer> answers = command.answers().stream().map(answerDTO -> {
            Question question = questionRepository.findById(answerDTO.questionId()).orElseThrow(() -> new IllegalArgumentException("Question not found"));

            Answer answer = new Answer();

            answer.setQuestion(question);
            answer.setTest(testSaved);
            answer.setValue(answerDTO.value());

            return answer;
        }).toList();

        var answersSaved = this.answerRepository.saveAll(answers);

        test.setAnswers(answersSaved);

        return Optional.of(testSaved);
    }

    @Override
    public Optional<Test> handle(DeleteTestCommand command) {
        Test test = testRepository.findById(command.id()).orElseThrow(() -> new IllegalArgumentException("Test not found"));

        this.testRepository.delete(test);

        return Optional.of(test);
    }
}
