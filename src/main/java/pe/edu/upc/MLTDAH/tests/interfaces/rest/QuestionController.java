package pe.edu.upc.MLTDAH.tests.interfaces.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.MLTDAH.tests.domain.model.commands.DeleteCategoryCommand;
import pe.edu.upc.MLTDAH.tests.domain.model.commands.DeleteQuestionCommand;
import pe.edu.upc.MLTDAH.tests.domain.model.entities.Category;
import pe.edu.upc.MLTDAH.tests.domain.model.entities.Question;
import pe.edu.upc.MLTDAH.tests.domain.model.queries.*;
import pe.edu.upc.MLTDAH.tests.domain.services.QuestionCommandService;
import pe.edu.upc.MLTDAH.tests.domain.services.QuestionQueryService;
import pe.edu.upc.MLTDAH.tests.interfaces.rest.resources.CreateCategoryResource;
import pe.edu.upc.MLTDAH.tests.interfaces.rest.resources.CreateQuestionResource;
import pe.edu.upc.MLTDAH.tests.interfaces.rest.resources.UpdateCategoryResource;
import pe.edu.upc.MLTDAH.tests.interfaces.rest.resources.UpdateQuestionResource;
import pe.edu.upc.MLTDAH.tests.interfaces.rest.transform.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/questions")
public class QuestionController {
    private final QuestionCommandService questionCommandService;
    private final QuestionQueryService questionQueryService;

    public QuestionController(QuestionCommandService questionCommandService, QuestionQueryService questionQueryService) {
        this.questionCommandService = questionCommandService;
        this.questionQueryService = questionQueryService;
    }

    @PostMapping
    public ResponseEntity<?> addQuestion(@RequestBody CreateQuestionResource createQuestionResource) {
        try {
            Optional<Question> question = questionCommandService.handle(CreateQuestionCommandFromResourceAssembler.toCommandFromResource(createQuestionResource));

            if (question.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", "Failed to register category"));
            }

            return question.map(source -> ResponseEntity.ok(QuestionResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", ex.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllQuestions() {
        try {
            List<Question> questions = questionQueryService.handle(new GetAllQuestionsQuery());

            if (questions.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }

            return ResponseEntity.ok(questions.stream().map(QuestionResourceFromEntityAssembler::toResourceFromEntity).toList());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", ex.getMessage()));
        }
    }

    @GetMapping("/exam/{examId}")
    public ResponseEntity<?> getAllQuestionsByExamId(@PathVariable Long examId) {
        try {
            List<Question> questions = questionQueryService.handle(new GetAllQuestionsByExamIdQuery(examId));

            if (questions.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }

            return ResponseEntity.ok(questions.stream().map(QuestionResourceFromEntityAssembler::toResourceFromEntity).toList());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", ex.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getQuestion(@PathVariable Long id) {
        try {
            Optional<Question> question = questionQueryService.handle(new GetQuestionByIdQuery(id));

            if (question.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }

            return question.map(source -> ResponseEntity.ok(QuestionResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", ex.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateQuestion(@PathVariable Long id, @RequestBody UpdateQuestionResource updateQuestionResource) {
        try {
            Optional<Question> question =  questionCommandService.handle(UpdateQuestionCommandFromResourceAssembler.toCommandFromResource(updateQuestionResource), id);

            if (question.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", "Failed to updated question"));
            }

            return question.map(source -> ResponseEntity.ok(QuestionResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", ex.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long id) {
        try {
            Optional<Question> question = questionCommandService.handle(new DeleteQuestionCommand(id));

            if(question.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }

            return question.map(source -> ResponseEntity.ok(QuestionResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", ex.getMessage()));
        }
    }
}
