package pe.edu.upc.MLTDAH.students.interfaces.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.MLTDAH.students.domain.model.entities.Gender;
import pe.edu.upc.MLTDAH.students.domain.model.queries.GetAllGendersQuery;
import pe.edu.upc.MLTDAH.students.domain.model.queries.GetGenderByIdQuery;
import pe.edu.upc.MLTDAH.students.domain.services.GenderQueryService;
import pe.edu.upc.MLTDAH.students.interfaces.rest.resources.GenderResource;
import pe.edu.upc.MLTDAH.students.interfaces.rest.transform.GenderResourceFromEntityAssembler;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/gender")
public class GenderController {
    private final GenderQueryService genderQueryService;

    public GenderController(GenderQueryService genderQueryService) {
        this.genderQueryService = genderQueryService;
    }

    @GetMapping
    public ResponseEntity<List<GenderResource>> getRoles() {
        List<Gender> genders = genderQueryService.handle(new GetAllGendersQuery());

        if(genders.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(genders.stream().map(GenderResourceFromEntityAssembler::toResourceFromEntity).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRoleByName(@PathVariable Long id) {
        try {
            Optional<Gender> gender = genderQueryService.handle(new GetGenderByIdQuery(id));

            if (gender.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }

            return gender.map(source -> ResponseEntity.ok(GenderResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet( () -> ResponseEntity.notFound().build());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", ex.getMessage()));
        }
    }
}
