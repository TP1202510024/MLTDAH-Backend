package pe.edu.upc.MLTDAH.iam.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import pe.edu.upc.MLTDAH.iam.domain.model.commands.CreateUserCommand;
import pe.edu.upc.MLTDAH.iam.domain.model.commands.SignUpCommand;
import pe.edu.upc.MLTDAH.iam.domain.model.entities.Role;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class User extends AbstractAggregateRoot<User> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(nullable = false)
    @Getter
    @Setter
    private String firstName;

    @Column(nullable = false)
    @Getter
    @Setter
    private String lastName;

    @Column(nullable = false)
    @Getter
    @Setter
    private String dni;

    @Column(nullable = false)
    @Getter
    @Setter
    private Date birthDate;

    @Column(nullable = false)
    @Getter
    @Setter
    private String photo;

    @Column(nullable = false)
    @Getter
    @Setter
    private String email;

    @Column(nullable = false)
    @Getter
    @Setter
    private String password;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")
    @Getter
    @Setter
    private Role role;

    @ManyToOne
    @JoinColumn(name = "institution_id")
    @Getter
    @Setter
    private Institution institution;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    @Getter
    private Date createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updatedAt;

    protected User() {

    }

    public User(SignUpCommand command, Institution institution, Role role) {
        setFirstName(command.firstName());
        setLastName(command.lastName());
        setDni(command.dni());
        setBirthDate(command.birthDate());
        setPhoto(command.photo());
        setEmail(command.email());
        setPassword(command.password());
        setInstitution(institution);
        setRole(role);
    }

    public User(CreateUserCommand command, Institution institution, Role role) {
        setFirstName(command.firstName());
        setLastName(command.lastName());
        setDni(command.dni());
        setBirthDate(command.birthDate());
        setPhoto(command.photo());
        setEmail(command.email());
        setPassword(command.password());
        setInstitution(institution);
        setRole(role);
    }
}