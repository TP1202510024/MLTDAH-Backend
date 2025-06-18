package pe.edu.upc.MLTDAH.iam.application.internal.commandservices;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;
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
import pe.edu.upc.MLTDAH.uploads.application.internal.outboundservices.S3Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class UserCommandServiceImplementation  implements UserCommandService {
    private final UserRepository userRepository;
    private final InstitutionRepository institutionRepository;
    private final RoleRepository roleRepository;
    private final HashingService hashingService;
    private final TokenService tokenService;
    private final TokenServiceImplementation tokenServiceImplementation;
    private final S3Service s3Service;
    public UserCommandServiceImplementation(UserRepository userRepository, InstitutionRepository institutionRepository, RoleRepository roleRepository, HashingService hashingService, TokenService tokenService, TokenServiceImplementation tokenServiceImplementation, S3Service s3Service) {
        this.userRepository = userRepository;
        this.institutionRepository = institutionRepository;
        this.roleRepository = roleRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
        this.tokenServiceImplementation = tokenServiceImplementation;
        this.s3Service = s3Service;
    }

    @Override
    public Optional<User> handle(SignUpCommand command) {
        Institution institution = this.institutionRepository.findById(command.institutionId()).orElseThrow(() -> new IllegalArgumentException("Institution not found"));
        Role role = this.roleRepository.findByName(Roles.REPRESENTATIVE).orElseThrow(() -> new IllegalArgumentException("Role not found"));

        SignUpCommand  signUpCommandHashingPassword = new SignUpCommand(command.firstName(), command.lastName(), command.dni(), command.birthDate(), command.photo(), command.email(), hashingService.encode(command.password()), command.institutionId());

        User user = new User(signUpCommandHashingPassword, institution, role);
        var userSaved = this.userRepository.save(user);

        return Optional.of(userSaved);
    }

    @Override
    public Optional<User> handle(CreateUserCommand command) {
        Institution institution = this.institutionRepository.findById(command.institutionId()).orElseThrow(() -> new IllegalArgumentException("Institution not found"));
        Role role = this.roleRepository.findByName(Roles.TEACHER).orElseThrow(() -> new IllegalArgumentException("Role not found"));

        CreateUserCommand  createUserCommandHashingPassword = new CreateUserCommand(command.firstName(), command.lastName(), command.dni(), command.birthDate(), command.photo(), command.email(), hashingService.encode(command.password()), command.institutionId());

        User user = new User(createUserCommandHashingPassword, institution, role);
        var userSaved = this.userRepository.save(user);

        return Optional.of(userSaved);
    }

    @Override
    public Optional<User> handle(UpdateUserCommand command, Long id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));

        user.setFirstName(command.firstName());
        user.setLastName(command.lastName());
        user.setDni(command.dni());
        user.setBirthDate(command.birthDate());
        user.setPhoto(command.photo());

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
}
