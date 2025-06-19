package pe.edu.upc.MLTDAH.iam.interfaces.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pe.edu.upc.MLTDAH.iam.domain.model.aggregates.User;
import pe.edu.upc.MLTDAH.iam.domain.model.commands.DeleteUserCommand;
import pe.edu.upc.MLTDAH.iam.domain.model.commands.UploadUserImageCommand;
import pe.edu.upc.MLTDAH.iam.domain.model.queries.GetAllUsersByInstitutionIdQuery;
import pe.edu.upc.MLTDAH.iam.domain.model.queries.GetAllUsersQuery;
import pe.edu.upc.MLTDAH.iam.domain.model.queries.GetUserByIdQuery;
import pe.edu.upc.MLTDAH.iam.domain.services.UserCommandService;
import pe.edu.upc.MLTDAH.iam.domain.services.UserQueryService;
import pe.edu.upc.MLTDAH.iam.interfaces.rest.resources.CreateUserResource;
import pe.edu.upc.MLTDAH.iam.interfaces.rest.resources.UpdateUserResource;
import pe.edu.upc.MLTDAH.iam.interfaces.rest.resources.UserResource;
import pe.edu.upc.MLTDAH.iam.interfaces.rest.transform.CreateUserCommandFromResourceAssembler;
import pe.edu.upc.MLTDAH.iam.interfaces.rest.transform.UpdateUserCommandFromResourceAssembler;
import pe.edu.upc.MLTDAH.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    public UserController(UserCommandService userCommandService, UserQueryService userQueryService) {
        this.userCommandService = userCommandService;
        this.userQueryService = userQueryService;
    }

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody CreateUserResource createUserResource) {
        try {
            Optional<User> user = userCommandService.handle(CreateUserCommandFromResourceAssembler.toCommandFromResource(createUserResource));

            if (user.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", "Failed to register user"));
            }

            return user.map(source -> ResponseEntity.ok(UserResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", ex.getMessage()));
        }
    }

    @PostMapping(value = "/upload-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addImageUser(@RequestPart("file") MultipartFile file, @RequestParam("id") Long id) {
        try {
            Optional<User> user = userCommandService.handle(new UploadUserImageCommand(file), id);

            if (user.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", "Failed to updated image user"));
            }

            return user.map(source -> ResponseEntity.ok(UserResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
        } catch (IllegalArgumentException | IOException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", ex.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UpdateUserResource updateUserResource) {
        try {
            Optional<User> user = userCommandService.handle(UpdateUserCommandFromResourceAssembler.toCommandFromResource(updateUserResource), id);

            if (user.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", "Failed to update user"));
            }

            return user.map(source -> ResponseEntity.ok(UserResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", ex.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userQueryService.handle(new GetAllUsersQuery());

        if(users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.ok(users.stream().map(UserResourceFromEntityAssembler::toResourceFromEntity).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        try {
            Optional<User> user = userQueryService.handle(new GetUserByIdQuery(id));

            if(user.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }

            return user.map(source -> ResponseEntity.ok(UserResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", ex.getMessage()));
        }
    }

    @GetMapping("/institution/{id}")
    public ResponseEntity<?> getUsersByInstitutionId(@PathVariable Long id) {
        try {
            List<User> users = userQueryService.handle(new GetAllUsersByInstitutionIdQuery(id));

            if (users.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }

            return ResponseEntity.ok(users.stream().map(UserResourceFromEntityAssembler::toResourceFromEntity).toList());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", ex.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            Optional<User> user = userCommandService.handle(new DeleteUserCommand(id));

            if(user.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }

            return user.map(source -> ResponseEntity.ok(UserResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", ex.getMessage()));
        }
    }
}
