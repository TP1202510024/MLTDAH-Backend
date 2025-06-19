package pe.edu.upc.MLTDAH.iam.interfaces.rest;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.MLTDAH.iam.domain.model.aggregates.Institution;
import pe.edu.upc.MLTDAH.iam.domain.model.aggregates.User;
import pe.edu.upc.MLTDAH.iam.domain.model.commands.GenerateForgotPasswordCommand;
import pe.edu.upc.MLTDAH.iam.domain.model.commands.UpdatePasswordCommand;
import pe.edu.upc.MLTDAH.iam.domain.model.commands.ValidateForgotPasswordCommand;
import pe.edu.upc.MLTDAH.iam.domain.services.InstitutionCommandService;
import pe.edu.upc.MLTDAH.iam.domain.services.UserCommandService;
import pe.edu.upc.MLTDAH.iam.interfaces.rest.resources.*;
import pe.edu.upc.MLTDAH.iam.interfaces.rest.transform.*;

import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/authentication")
public class AuthenticationController {
    private final UserCommandService userCommandService;
    private final InstitutionCommandService institutionCommandService;

    public AuthenticationController(UserCommandService userCommandService, InstitutionCommandService institutionCommandService) {
        this.userCommandService = userCommandService;
        this.institutionCommandService = institutionCommandService;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody SignInResource signInResource) {
        try {
            Optional<ImmutablePair<User, String>> user = userCommandService.handle(SignInCommandFromResourceAssembler.toCommandFromResource(signInResource));

            if (user.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("message", "User not found"));
            }

            return ResponseEntity.ok(AuthenticatedUserResourceFromEntityAssembler.toResourceFromEntity(user.get().getLeft(), user.get().getRight()));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", ex.getMessage()));
        }
    }


    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody SignUpResource signUpResource) {
        try {
            Optional<Institution> institution = institutionCommandService.handle(SignUpCommandFromResourceAssembler.toInstitutionCommandFromResource(signUpResource));

            if(institution.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("message", "Failed to register institution"));
            }

            Optional<User> user = userCommandService.handle(SignUpCommandFromResourceAssembler.toUserCommandFromResource(signUpResource, institution.get().getId()));

            if(user.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", "Failed to register user"));
            }

            return user.map(source -> ResponseEntity.ok(UserResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", ex.getMessage()));
        }
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody GenerateForgotPasswordResource generateForgotPasswordResource) {
        try {

           userCommandService.handle(GenerateForgotPasswordCommandFromResourceAssembler.toCommandFromResource(generateForgotPasswordResource));

            return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("message", "restart code sent"));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", ex.getMessage()));
        }
    }

    @PostMapping("/forgot-password/validate")
    public ResponseEntity<?> forgotPasswordValidateCode(@RequestBody ValidateForgotPasswordResource validateForgotPasswordResource) {
        try {

            boolean isValidRestartCode = userCommandService.handle(ValidateForgotPasswordCommandFromResourceAssembler.toCommandFromResource(validateForgotPasswordResource));

            if(isValidRestartCode) {
                return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("valid", true));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("valid", false));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", ex.getMessage()));
        }
    }

    @PostMapping("/forgot-password/change-password")
    public ResponseEntity<?> forgotPasswordChangePassword(@RequestBody UpdatePasswordResource updatePasswordResource) {
        try {
            boolean isUpdatedPassword = userCommandService.handle(UpdatePasswordCommandFromResourceAssembler.toCommandFromResource(updatePasswordResource));

            if(isUpdatedPassword) {
                return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("message", "The password is updated"));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", "Error to change the password"));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", ex.getMessage()));
        }
    }
}
