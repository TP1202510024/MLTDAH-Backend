package pe.edu.upc.MLTDAH.iam.application.internal.commandservices;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;
import pe.edu.upc.MLTDAH.iam.application.internal.outboundservices.EmailService;
import pe.edu.upc.MLTDAH.iam.application.internal.outboundservices.HashingService;
import pe.edu.upc.MLTDAH.iam.application.internal.outboundservices.TokenService;
import pe.edu.upc.MLTDAH.iam.domain.model.aggregates.Institution;
import pe.edu.upc.MLTDAH.iam.domain.model.aggregates.User;
import pe.edu.upc.MLTDAH.iam.domain.model.commands.*;
import pe.edu.upc.MLTDAH.iam.domain.model.entities.Role;
import pe.edu.upc.MLTDAH.iam.domain.model.valueobjects.Roles;
import pe.edu.upc.MLTDAH.iam.domain.services.UserCommandService;
import pe.edu.upc.MLTDAH.iam.infrastructure.persistence.jpa.InstitutionRepository;
import pe.edu.upc.MLTDAH.iam.infrastructure.persistence.jpa.RoleRepository;
import pe.edu.upc.MLTDAH.iam.infrastructure.persistence.jpa.UserRepository;
import pe.edu.upc.MLTDAH.iam.infrastructure.services.TokenServiceImplementation;
import pe.edu.upc.MLTDAH.notifications.domain.model.commands.CreateNotificationCommand;
import pe.edu.upc.MLTDAH.notifications.domain.services.NotificationCommandService;
import pe.edu.upc.MLTDAH.uploads.application.internal.outboundservices.S3Service;

import java.io.IOException;
import java.util.Optional;
import java.util.Random;

@Service
public class UserCommandServiceImplementation  implements UserCommandService {
    private final UserRepository userRepository;
    private final InstitutionRepository institutionRepository;
    private final RoleRepository roleRepository;
    private final HashingService hashingService;
    private final TokenService tokenService;
    private final TokenServiceImplementation tokenServiceImplementation;
    private final S3Service s3Service;
    private final NotificationCommandService notificationCommandService;
    private final EmailService emailService;

    public UserCommandServiceImplementation(UserRepository userRepository, InstitutionRepository institutionRepository, RoleRepository roleRepository, HashingService hashingService, TokenService tokenService, TokenServiceImplementation tokenServiceImplementation, S3Service s3Service, NotificationCommandService notificationCommandService, EmailService emailService) {
        this.userRepository = userRepository;
        this.institutionRepository = institutionRepository;
        this.roleRepository = roleRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
        this.tokenServiceImplementation = tokenServiceImplementation;
        this.s3Service = s3Service;
        this.notificationCommandService = notificationCommandService;
        this.emailService = emailService;
    }

    @Override
    public Optional<User> handle(SignUpCommand command) {
        Institution institution = this.institutionRepository.findById(command.institutionId()).orElseThrow(() -> new IllegalArgumentException("Institution not found"));
        Role role = this.roleRepository.findByName(Roles.REPRESENTATIVE).orElseThrow(() -> new IllegalArgumentException("Role not found"));

        SignUpCommand  signUpCommandHashingPassword = new SignUpCommand(command.firstName(), command.lastName(), command.dni(), command.birthDate(), command.email(), hashingService.encode(command.password()), command.institutionId());

        User user = new User(signUpCommandHashingPassword, institution, role);
        var userSaved = this.userRepository.save(user);

        return Optional.of(userSaved);
    }

    @Override
    public Optional<User> handle(CreateUserCommand command) {
        Institution institution = this.institutionRepository.findById(command.institutionId()).orElseThrow(() -> new IllegalArgumentException("Institution not found"));
        Role role = this.roleRepository.findById(command.roleId()).orElseThrow(() -> new IllegalArgumentException("Role not found"));

        CreateUserCommand  createUserCommandHashingPassword = new CreateUserCommand(command.firstName(), command.lastName(), command.dni(), command.birthDate(), command.email(), hashingService.encode(command.password()), command.institutionId(), command.roleId());

        User user = new User(createUserCommandHashingPassword, institution, role);
        var userSaved = this.userRepository.save(user);

        this.notificationCommandService.handle(new CreateNotificationCommand("Se ha creado al usuario " + userSaved.getFirstName() + " " + userSaved.getLastName(), "El usuario creado tiene el rol: " + role.getStringName(), "Usuario", userSaved.getId(), userSaved.getInstitution().getId(), role.getName().name()));

        return Optional.of(userSaved);
    }

    @Override
    public Optional<User> handle(UpdateUserCommand command, Long id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));

        user.setFirstName(command.firstName());
        user.setLastName(command.lastName());
        user.setDni(command.dni());
        user.setBirthDate(command.birthDate());

        var userSaved = this.userRepository.save(user);

        return Optional.of(userSaved);
    }

    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
        User user = userRepository.findByEmail(command.email()).orElseThrow(() -> new IllegalArgumentException("User not found"));

        if(!hashingService.matches(command.password(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid Password");
        }

        String token = tokenService.generateToken(user.getEmail());
        System.out.println(token + " " + tokenService.getEmailFromToken(token) + " " + tokenService.validateToken(token));
        return Optional.of(ImmutablePair.of(user, token));
    }

    @Override
    public Optional<User> handle(DeleteUserCommand command) {
        User user = this.userRepository.findById(command.id()).orElseThrow(() -> new IllegalArgumentException("User not found"));

        this.userRepository.delete(user);

        this.notificationCommandService.handle(new CreateNotificationCommand("Se ha eliminado al usuario " + user.getFirstName() + " " + user.getLastName(), "El usuario eliminado tiene el rol: " + user.getRole().getStringName(), "Usuario", Long.parseLong("0"), user.getInstitution().getId(), user.getRole().getName().name()));

        return Optional.of(user);
    }

    @Override
    public Optional<User> handle(UploadUserImageCommand command, Long id) throws IOException {
        User user = this.userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));

        String userImageUrl = this.s3Service.uploadFile(command.file());
        user.setPhoto(userImageUrl);

        var userSaved = this.userRepository.save(user);

        return Optional.of(userSaved);
    }

    @Override
    public void handle(GenerateForgotPasswordCommand command) {
        User user = this.userRepository.findByEmail(command.email()).orElseThrow(() -> new IllegalArgumentException("User not found"));

        Random random = new Random();
        int restartCode = 100000 + random.nextInt(900000);

        user.setRestartCode(String.valueOf(restartCode));

        this.userRepository.save(user);

        this.emailService.sendRestartCodeEmail(user.getEmail(), String.valueOf(restartCode));
    }

    @Override
    public boolean handle(ValidateForgotPasswordCommand command) {
        return userRepository.findByRestartCode(command.restartCode()).isPresent();
    }

    @Override
    public boolean handle(UpdatePasswordCommand command) {
        if (!command.password().equals(command.repeatedPassword())) {
            return false;
        }

        return userRepository.findByRestartCode(command.restartCode())
                .map(user -> {
                    user.setPassword(hashingService.encode(command.password()));
                    user.setRestartCode(null);
                    userRepository.save(user);
                    return true;
                })
                .orElse(false);
    }
}
