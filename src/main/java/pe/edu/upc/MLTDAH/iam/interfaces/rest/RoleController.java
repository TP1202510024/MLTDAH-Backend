package pe.edu.upc.MLTDAH.iam.interfaces.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.MLTDAH.iam.domain.model.entities.Role;
import pe.edu.upc.MLTDAH.iam.domain.model.queries.GetAllRolesQuery;
import pe.edu.upc.MLTDAH.iam.domain.model.queries.GetRoleByNameQuery;
import pe.edu.upc.MLTDAH.iam.domain.model.valueobjects.Roles;
import pe.edu.upc.MLTDAH.iam.domain.services.RoleCommandService;
import pe.edu.upc.MLTDAH.iam.domain.services.RoleQueryService;
import pe.edu.upc.MLTDAH.iam.interfaces.rest.resources.RoleResource;
import pe.edu.upc.MLTDAH.iam.interfaces.rest.transform.RoleResourceFormEntityAssembler;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/roles")
public class RoleController {
    private final RoleQueryService roleQueryService;

    public RoleController(RoleQueryService roleQueryService) {
        this.roleQueryService = roleQueryService;
    }

    @GetMapping
    public ResponseEntity<List<RoleResource>> getRoles() {
        List<Role> roles = roleQueryService.handle(new GetAllRolesQuery());

        if(roles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(roles.stream().map(RoleResourceFormEntityAssembler::toResourceFromEntity).toList());
    }

    @GetMapping("/{name}")
    public ResponseEntity<RoleResource> getRoleByName(@PathVariable String name) {
        try {
            Optional<Role> role = roleQueryService.handle(new GetRoleByNameQuery(Roles.valueOf(name.toUpperCase())));

            return role.map(source -> ResponseEntity.ok(RoleResourceFormEntityAssembler.toResourceFromEntity(source))).orElseGet( () -> ResponseEntity.noContent().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
