package pe.edu.upc.MLTDAH.students.interfaces.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pe.edu.upc.MLTDAH.iam.domain.model.aggregates.User;
import pe.edu.upc.MLTDAH.iam.domain.model.commands.UploadUserImageCommand;
import pe.edu.upc.MLTDAH.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
import pe.edu.upc.MLTDAH.students.domain.model.aggregates.Student;
import pe.edu.upc.MLTDAH.students.domain.model.commands.DeleteStudentCommand;
import pe.edu.upc.MLTDAH.students.domain.model.commands.UploadStudentImageCommand;
import pe.edu.upc.MLTDAH.students.domain.model.queries.*;
import pe.edu.upc.MLTDAH.students.domain.services.StudentCommandService;
import pe.edu.upc.MLTDAH.students.domain.services.StudentQueryService;
import pe.edu.upc.MLTDAH.students.interfaces.rest.resources.CreateStudentResource;
import pe.edu.upc.MLTDAH.students.interfaces.rest.resources.UpdateStudentResource;
import pe.edu.upc.MLTDAH.students.interfaces.rest.transform.CreateStudentCommandFromResourceAssembler;
import pe.edu.upc.MLTDAH.students.interfaces.rest.transform.StudentResourceFromEntityAssembler;
import pe.edu.upc.MLTDAH.students.interfaces.rest.transform.UpdateStudentCommandFromResourceAssembler;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {
    private final StudentCommandService studentCommandService;
    private final StudentQueryService studentQueryService;

    public StudentController(StudentCommandService studentCommandService, StudentQueryService studentQueryService) {
        this.studentCommandService = studentCommandService;
        this.studentQueryService = studentQueryService;
    }

    @PostMapping
    public ResponseEntity<?> addStudent(@RequestBody CreateStudentResource createStudentResource) {
        try {
            Optional<Student> student = studentCommandService.handle(CreateStudentCommandFromResourceAssembler.toCommandFromResource(createStudentResource));

            if (student.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", "Failed to register student"));
            }

            return student.map(source -> ResponseEntity.ok(StudentResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", ex.getMessage()));
        }
    }

    @PostMapping(value = "/upload-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addImageStudent(@RequestPart("file") MultipartFile file, @RequestParam("id") Long id) {
        try {
            Optional<Student> student = studentCommandService.handle(new UploadStudentImageCommand(file), id);

            if (student.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", "Failed to register student"));
            }

            return student.map(source -> ResponseEntity.ok(StudentResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
        } catch (IllegalArgumentException | IOException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", ex.getMessage()));
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Long id, @RequestBody UpdateStudentResource updateStudentResource) {
        try {
            Optional<Student> student = studentCommandService.handle(UpdateStudentCommandFromResourceAssembler.toCommandFromResource(updateStudentResource), id);

            if (student.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", "Failed to update student"));
            }

            return student.map(source -> ResponseEntity.ok(StudentResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", ex.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllStudents() {
        List<Student> students = studentQueryService.handle(new GetAllStudentsQuery());

        if(students.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.ok(students.stream().map(StudentResourceFromEntityAssembler::toResourceFromEntity).toList());
    }

    @GetMapping("/institution/{institutionId}")
    public ResponseEntity<?> getAllStudentsByInstitutionId(@PathVariable Long institutionId) {
        List<Student> students = studentQueryService.handle(new GetAllStudentsByInstitutionIdQuery(institutionId));

        if(students.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.ok(students.stream().map(StudentResourceFromEntityAssembler::toResourceFromEntity).toList());
    }

    @GetMapping("/institution/{institutionId}/grade/{gradeId}")
    public ResponseEntity<?> getAllStudentsByInstitutionIdAndGradeId(@PathVariable Long institutionId, @PathVariable Long gradeId) {
        List<Student> students = studentQueryService.handle(new GetAllStudentsByInstitutionIdAndGradeIdQuery(institutionId, gradeId));

        if(students.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.ok(students.stream().map(StudentResourceFromEntityAssembler::toResourceFromEntity).toList());
    }

    @GetMapping("/institution/{institutionId}/gender/{genderId}")
    public ResponseEntity<?> getAllStudentsByInstitutionIdAndGenderId(@PathVariable Long institutionId, @PathVariable Long genderId) {
        List<Student> students = studentQueryService.handle(new GetAllStudentsByInstitutionIdAndGenderIdQuery(institutionId, genderId));

        if(students.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.ok(students.stream().map(StudentResourceFromEntityAssembler::toResourceFromEntity).toList());
    }

    @GetMapping("/institution/{institutionId}/gender/{genderId}/grade/{gradeId}")
    public ResponseEntity<?> getAllStudentsByInstitutionIdGenderIdAndGrade(@PathVariable Long institutionId, @PathVariable Long genderId, @PathVariable Long gradeId) {
        List<Student> students = studentQueryService.handle(new GetAllStudentsByInstitutionIdAndGenderIdAndGradeIdQuery(institutionId, genderId, gradeId));

        if(students.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.ok(students.stream().map(StudentResourceFromEntityAssembler::toResourceFromEntity).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id) {
        try {
            Optional<Student> student = studentQueryService.handle(new GetStudentByIdQuery(id));

            if(student.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }

            return student.map(source -> ResponseEntity.ok(StudentResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", ex.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            Optional<Student> student =  studentCommandService.handle(new DeleteStudentCommand(id));

            if(student.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }

            return student.map(source -> ResponseEntity.ok(StudentResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", ex.getMessage()));
        }
    }
}
