package pe.edu.upc.MLTDAH.iam.application.internal.commandservices;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;
import pe.edu.upc.MLTDAH.iam.domain.model.aggregates.Institution;
import pe.edu.upc.MLTDAH.iam.domain.model.aggregates.User;
import pe.edu.upc.MLTDAH.iam.domain.model.commands.*;
import pe.edu.upc.MLTDAH.iam.domain.model.entities.Role;
import pe.edu.upc.MLTDAH.iam.domain.services.UserCommandService;
import pe.edu.upc.MLTDAH.iam.infrastructure.persistence.jpa.InstitutionRepository;
import pe.edu.upc.MLTDAH.iam.infrastructure.persistence.jpa.UserRepository;

import java.util.Optional;

@Service
public class UserCommandServiceImplementation  implements UserCommandService {
    private final UserRepository userRepository;
    private final InstitutionRepository institutionRepository;
    public UserCommandServiceImplementation(UserRepository userRepository, InstitutionRepository institutionRepository) {
        this.userRepository = userRepository;
        this.institutionRepository = institutionRepository;
    }

    @Override
    public Optional<User> handle(SignUpCommand command) {
        Institution institution = this.institutionRepository.findById(command.institutionId()).orElseThrow(() -> new IllegalArgumentException("Institution not found"));
        Role role = Role.toRoleFromName(command.role());

        User user = new User(command, institution, role);
        var userSaved = this.userRepository.save(user);

        return Optional.of(userSaved);
    }

    @Override
    public Optional<User> handle(CreateUserCommand command) {
        Institution institution = this.institutionRepository.findById(command.institutionId()).orElseThrow(() -> new IllegalArgumentException("Institution not found"));
        Role role = Role.toRoleFromName(command.role());

        User user = new User(command, institution, role);
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

        if(!user.getPassword().equals(command.password())) {
            throw new IllegalArgumentException("Invalid Password");
        }

        return Optional.of(ImmutablePair.of(user, ""));
    }

    @Override
    public Optional<User> handle(DeleteUserCommand command) {
        User user = this.userRepository.findById(command.id()).orElseThrow(() -> new IllegalArgumentException("User not found"));

        this.userRepository.delete(user);

        return Optional.of(user);
    }
}
