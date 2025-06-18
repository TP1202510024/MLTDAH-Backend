package pe.edu.upc.MLTDAH.tests.interfaces.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.MLTDAH.tests.domain.model.aggregates.Exam;
import pe.edu.upc.MLTDAH.tests.domain.model.aggregates.Test;
import pe.edu.upc.MLTDAH.tests.domain.model.commands.DeleteExamCommand;
import pe.edu.upc.MLTDAH.tests.domain.model.commands.DeleteTestCommand;
import pe.edu.upc.MLTDAH.tests.domain.model.entities.Question;
import pe.edu.upc.MLTDAH.tests.domain.model.queries.*;
import pe.edu.upc.MLTDAH.tests.domain.services.TestCommandService;
import pe.edu.upc.MLTDAH.tests.domain.services.TestQueryService;
import pe.edu.upc.MLTDAH.tests.interfaces.rest.resources.CreateExamResource;
import pe.edu.upc.MLTDAH.tests.interfaces.rest.resources.CreateTestResource;
import pe.edu.upc.MLTDAH.tests.interfaces.rest.transform.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/tests")
public class TestController {
    private final TestCommandService testCommandService;
    private final TestQueryService testQueryService;

    public TestController(TestCommandService testCommandService, TestQueryService testQueryService) {
        this.testCommandService = testCommandService;
        this.testQueryService = testQueryService;
    }

    @PostMapping
    public ResponseEntity<?> addTest(@RequestBody CreateTestResource createTestResource) {
        try {
            Optional<Test> test = testCommandService.handle(CreateTestCommandFromResourceAssembler.toCommandFromResource(createTestResource));

            if (test.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", "Failed to register test"));
            }

            return test.map(source -> ResponseEntity.ok(TestResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", ex.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllTests() {
        try {
            List<Test> tests = testQueryService.handle(new GetAllTestsQuery());

            if (tests.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }

            return ResponseEntity.ok(tests.stream().map(TestResourceFromEntityAssembler::toResourceFromEntity).toList());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", ex.getMessage()));
        }
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<?> getAllTestsByStudentId(@PathVariable Long studentId) {
        try {
            List<Test> tests = testQueryService.handle(new GetAllTestsByStudentIdQuery(studentId));

            if (tests.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }

            return ResponseEntity.ok(tests.stream().map(TestResourceFromEntityAssembler::toResourceFromEntity).toList());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", ex.getMessage()));
        }
    }

    @GetMapping("/student/{studentId}/year/{year}")
    public ResponseEntity<?> getAllTestsByStudentIdAndYear(@PathVariable Long studentId, @PathVariable Long year) {
        try {
            List<Test> tests = testQueryService.handle(new GetAllTestByStudentIdAndYearQuery(studentId, year));

            if (tests.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }

            return ResponseEntity.ok(tests.stream().map(TestResourceFromEntityAssembler::toResourceFromEntity).toList());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", ex.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTestsById(@PathVariable Long id) {
        try {
            Optional<Test> test = testQueryService.handle(new GetTestByIdQuery(id));

            if (test.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }

            return test.map(source -> ResponseEntity.ok(TestResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", ex.getMessage()));
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTest(@PathVariable Long id) {
        try {
            Optional<Test> test = testCommandService.handle(new DeleteTestCommand(id));

            if(test.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }

            return test.map(source -> ResponseEntity.ok(TestResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", ex.getMessage()));
        }
    }

}
