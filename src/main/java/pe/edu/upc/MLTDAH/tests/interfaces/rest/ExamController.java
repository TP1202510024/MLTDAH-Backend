package pe.edu.upc.MLTDAH.tests.interfaces.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.MLTDAH.tests.domain.model.aggregates.Exam;
import pe.edu.upc.MLTDAH.tests.domain.model.commands.CreateExamCommand;
import pe.edu.upc.MLTDAH.tests.domain.model.commands.DeleteCategoryCommand;
import pe.edu.upc.MLTDAH.tests.domain.model.commands.DeleteExamCommand;
import pe.edu.upc.MLTDAH.tests.domain.model.entities.Category;
import pe.edu.upc.MLTDAH.tests.domain.model.queries.GetAllCategoriesQuery;
import pe.edu.upc.MLTDAH.tests.domain.model.queries.GetAllExamsQuery;
import pe.edu.upc.MLTDAH.tests.domain.model.queries.GetCategoryByIdQuery;
import pe.edu.upc.MLTDAH.tests.domain.model.queries.GetExamByIdQuery;
import pe.edu.upc.MLTDAH.tests.domain.services.ExamCommandService;
import pe.edu.upc.MLTDAH.tests.domain.services.ExamQueryService;
import pe.edu.upc.MLTDAH.tests.interfaces.rest.resources.CreateCategoryResource;
import pe.edu.upc.MLTDAH.tests.interfaces.rest.resources.CreateExamResource;
import pe.edu.upc.MLTDAH.tests.interfaces.rest.resources.UpdateCategoryResource;
import pe.edu.upc.MLTDAH.tests.interfaces.rest.resources.UpdateExamResource;
import pe.edu.upc.MLTDAH.tests.interfaces.rest.transform.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/exams")
public class ExamController {
    private final ExamCommandService examCommandService;
    private final ExamQueryService examQueryService;

    public ExamController(ExamCommandService examCommandService, ExamQueryService examQueryService) {
        this.examCommandService = examCommandService;
        this.examQueryService = examQueryService;
    }

    @PostMapping
    public ResponseEntity<?> addExam(@RequestBody CreateExamResource createExamResource) {
        try {
            Optional<Exam> exam = examCommandService.handle(CreateExamCommandFromResourceAssembler.toCommandFromResource(createExamResource));

            if (exam.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", "Failed to register exam"));
            }

            return exam.map(source -> ResponseEntity.ok(ExamResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", ex.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllExams() {
        try {
            List<Exam> exams = examQueryService.handle(new GetAllExamsQuery());

            if (exams.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }

            return ResponseEntity.ok(exams.stream().map(ExamResourceFromEntityAssembler::toResourceFromEntity).toList());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", ex.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getExam(@PathVariable Long id) {
        try {
            Optional<Exam> exam = examQueryService.handle(new GetExamByIdQuery(id));

            if (exam.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }

            return exam.map(source -> ResponseEntity.ok(ExamResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", ex.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateExam(@PathVariable Long id, @RequestBody UpdateExamResource updateExamResource) {
        try {
            Optional<Exam> exam =  examCommandService.handle(UpdateExamCommandFromResourceAssembler.toCommandFromResource(updateExamResource), id);

            if (exam.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", "Failed to updated exam"));
            }

            return exam.map(source -> ResponseEntity.ok(ExamResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", ex.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExam(@PathVariable Long id) {
        try {
            Optional<Exam> exam = examCommandService.handle(new DeleteExamCommand(id));

            if(exam.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }

            return exam.map(source -> ResponseEntity.ok(ExamResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", ex.getMessage()));
        }
    }
}
