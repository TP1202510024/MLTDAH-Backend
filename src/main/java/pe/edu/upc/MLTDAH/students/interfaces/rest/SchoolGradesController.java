package pe.edu.upc.MLTDAH.students.interfaces.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.MLTDAH.students.domain.model.entities.SchoolGrade;
import pe.edu.upc.MLTDAH.students.domain.model.queries.GetAllSchoolGradesQuery;
import pe.edu.upc.MLTDAH.students.domain.model.queries.GetSchoolGradeByIdQuery;
import pe.edu.upc.MLTDAH.students.domain.services.SchoolGradeQueryService;
import pe.edu.upc.MLTDAH.students.interfaces.rest.resources.SchoolGradeResource;
import pe.edu.upc.MLTDAH.students.interfaces.rest.transform.SchoolGradeResourceFromEntityAssembler;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/school-grades")
public class SchoolGradesController {
    private final SchoolGradeQueryService schoolGradeQueryService;

    public SchoolGradesController(SchoolGradeQueryService schoolGradeQueryService) {
        this.schoolGradeQueryService = schoolGradeQueryService;
    }

    @GetMapping
    public ResponseEntity<List<SchoolGradeResource>> getRoles() {
        List<SchoolGrade> schoolGrades = schoolGradeQueryService.handle(new GetAllSchoolGradesQuery());

        if(schoolGrades.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(schoolGrades.stream().map(SchoolGradeResourceFromEntityAssembler::toResourceFromEntity).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRoleByName(@PathVariable Long id) {
        try {
            Optional<SchoolGrade> schoolGrade = schoolGradeQueryService.handle(new GetSchoolGradeByIdQuery(id));

            if (schoolGrade.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }

            return schoolGrade.map(source -> ResponseEntity.ok(SchoolGradeResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet( () -> ResponseEntity.notFound().build());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", ex.getMessage()));
        }
    }
}
