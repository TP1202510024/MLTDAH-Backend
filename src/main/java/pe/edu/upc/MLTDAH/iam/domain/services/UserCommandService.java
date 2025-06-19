package pe.edu.upc.MLTDAH.iam.domain.services;

import org.apache.commons.lang3.tuple.ImmutablePair;
import pe.edu.upc.MLTDAH.iam.domain.model.aggregates.User;
import pe.edu.upc.MLTDAH.iam.domain.model.commands.*;

import java.io.IOException;
import java.util.Optional;

public interface UserCommandService {
    Optional<User> handle(SignUpCommand command);
    Optional<User> handle(CreateUserCommand command);
    Optional<User> handle(UpdateUserCommand command, Long id);
    Optional<ImmutablePair<User, String>>  handle(SignInCommand command);
    Optional<User> handle(DeleteUserCommand command);
    Optional<User> handle(UploadUserImageCommand command, Long id) throws IOException;
    void handle(GenerateForgotPasswordCommand command);
    boolean handle(ValidateForgotPasswordCommand command);
    boolean handle(UpdatePasswordCommand command);
}
