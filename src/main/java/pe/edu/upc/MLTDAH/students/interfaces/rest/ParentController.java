package pe.edu.upc.MLTDAH.students.interfaces.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.MLTDAH.students.domain.model.aggregates.Parent;
import pe.edu.upc.MLTDAH.students.domain.model.commands.DeleteParentCommand;
import pe.edu.upc.MLTDAH.students.domain.model.queries.GetParentByStudentIdQuery;
import pe.edu.upc.MLTDAH.students.domain.services.ParentCommandService;
import pe.edu.upc.MLTDAH.students.domain.services.ParentQueryService;
import pe.edu.upc.MLTDAH.students.interfaces.rest.resources.CreateParentResource;
import pe.edu.upc.MLTDAH.students.interfaces.rest.transform.CreateParentCommandFromResourceAssembler;
import pe.edu.upc.MLTDAH.students.interfaces.rest.transform.ParentResourceFromEntityAssembler;

import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/parents")
public class ParentController {
    private final ParentCommandService parentCommandService;
    private final ParentQueryService parentQueryService;

    public ParentController(ParentCommandService parentCommandService, ParentQueryService parentQueryService) {
        this.parentCommandService = parentCommandService;
        this.parentQueryService = parentQueryService;
    }

    @PostMapping
    public ResponseEntity<?> addParent(@RequestBody CreateParentResource createParentResource) {
        try {
            Optional<Parent> parent = parentCommandService.handle(CreateParentCommandFromResourceAssembler.toCommandFromResource(createParentResource));

            if (parent.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", "Failed to register parent"));
            }

            return parent.map(source -> ResponseEntity.ok(ParentResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", ex.getMessage()));
        }
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<?> getParent(@PathVariable Long studentId) {
        try {
            Optional<Parent> parent = parentQueryService.handle(new GetParentByStudentIdQuery(studentId));

            if (parent.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }

            return parent.map(source -> ResponseEntity.ok(ParentResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", ex.getMessage()));
        }
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<?> deleteParent(@PathVariable Long studentId) {
        try {
            Optional<Parent> parent =  parentCommandService.handle(new DeleteParentCommand(studentId));

            if(parent.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }

            return parent.map(source -> ResponseEntity.ok(ParentResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", ex.getMessage()));
        }
    }
}
