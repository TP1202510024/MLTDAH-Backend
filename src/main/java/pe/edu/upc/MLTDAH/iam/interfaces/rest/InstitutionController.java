package pe.edu.upc.MLTDAH.iam.interfaces.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pe.edu.upc.MLTDAH.iam.domain.model.aggregates.Institution;
import pe.edu.upc.MLTDAH.iam.domain.model.commands.UploadInstitutionImageCommand;
import pe.edu.upc.MLTDAH.iam.domain.model.queries.GetAllInstitutionsQuery;
import pe.edu.upc.MLTDAH.iam.domain.model.queries.GetInstitutionByIdQuery;
import pe.edu.upc.MLTDAH.iam.domain.services.InstitutionCommandService;
import pe.edu.upc.MLTDAH.iam.domain.services.InstitutionQueryService;
import pe.edu.upc.MLTDAH.iam.interfaces.rest.resources.InstitutionResource;
import pe.edu.upc.MLTDAH.iam.interfaces.rest.resources.UpdateInstitutionResource;
import pe.edu.upc.MLTDAH.iam.interfaces.rest.transform.InstitutionResourceFromEntityAssembler;
import pe.edu.upc.MLTDAH.iam.interfaces.rest.transform.UpdateInstitutionCommandFromResourceAssembler;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/institutions")
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

    @PostMapping(value = "/upload-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addImageInstitution(@RequestPart("file") MultipartFile file, @RequestParam("id") Long id) {
        try {
            Optional<Institution> institution = institutionCommandService.handle(new UploadInstitutionImageCommand(file), id);

            if (institution.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", "Failed to updated image institution"));
            }

            return institution.map(source -> ResponseEntity.ok(InstitutionResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IllegalArgumentException | IOException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", ex.getMessage()));
        }
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
