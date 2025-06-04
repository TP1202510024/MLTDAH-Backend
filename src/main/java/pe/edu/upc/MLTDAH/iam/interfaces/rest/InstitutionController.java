package pe.edu.upc.MLTDAH.iam.interfaces.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.MLTDAH.iam.domain.model.aggregates.Institution;
import pe.edu.upc.MLTDAH.iam.domain.model.queries.GetAllInstitutionsQuery;
import pe.edu.upc.MLTDAH.iam.domain.model.queries.GetInstitutionByIdQuery;
import pe.edu.upc.MLTDAH.iam.domain.services.InstitutionCommandService;
import pe.edu.upc.MLTDAH.iam.domain.services.InstitutionQueryService;
import pe.edu.upc.MLTDAH.iam.interfaces.rest.resources.InstitutionResource;
import pe.edu.upc.MLTDAH.iam.interfaces.rest.resources.UpdateInstitutionResource;
import pe.edu.upc.MLTDAH.iam.interfaces.rest.transform.InstitutionResourceFromEntityAssembler;
import pe.edu.upc.MLTDAH.iam.interfaces.rest.transform.UpdateInstitutionCommandFromResourceAssembler;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/institution")
public class InstitutionController {
    private final InstitutionCommandService institutionCommandService;
    private final InstitutionQueryService institutionQueryService;

    public InstitutionController(InstitutionCommandService institutionCommandService, InstitutionQueryService institutionQueryService) {
        this.institutionCommandService = institutionCommandService;
        this.institutionQueryService = institutionQueryService;
    }

    @GetMapping
    public ResponseEntity<List<InstitutionResource>> getAllInstitutions() {
        List<Institution> institutions = institutionQueryService.handle(new GetAllInstitutionsQuery());

        if(institutions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(institutions.stream().map(InstitutionResourceFromEntityAssembler::toResourceFromEntity).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstitutionResource> getInstitutionById(@PathVariable Long id) {
        Optional<Institution> institution = institutionQueryService.handle(new GetInstitutionByIdQuery(id));

        return institution.map(source -> ResponseEntity.ok(InstitutionResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<InstitutionResource> updateInstitution(@PathVariable Long id, @RequestBody UpdateInstitutionResource updateInstitutionResource) {
        Optional<Institution> institution = institutionCommandService.handle(UpdateInstitutionCommandFromResourceAssembler.toCommandFromResource(updateInstitutionResource), id);

        return institution.map(source -> ResponseEntity.ok(InstitutionResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
