package pe.edu.upc.MLTDAH.iam.infrastructure.authorization.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pe.edu.upc.MLTDAH.iam.domain.model.aggregates.User;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Getter
@EqualsAndHashCode
public class UserDetailsImplementation implements UserDetails {
    private final String firstName;
    private final String lastName;
    private final String dni;
    private final Date birthDate;
    private final String photo;
    private final String email;
    private final Long institutionId;
    @JsonIgnore
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;
    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;

    public UserDetailsImplementation(String firstName, String lastName, String dni, Date birthDate, String photo, String email, Long institutionId, String password, Collection<? extends GrantedAuthority> authorities) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dni = dni;
        this.birthDate = birthDate;
        this.photo = photo;
        this.email = email;
        this.institutionId = institutionId;
        this.password = password;
        this.authorities = authorities;
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
    }

    public static UserDetailsImplementation build(User user) {
        return new UserDetailsImplementation(user.getFirstName(), user.getLastName(), user.getDni(), user.getBirthDate(), user.getPhoto(), user.getEmail(), user.getInstitution().getId(), user.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().getStringName()))
        );
    }

    @Override
    public String getUsername() {
        return "";
    }
}
