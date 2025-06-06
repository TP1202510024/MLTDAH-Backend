package pe.edu.upc.MLTDAH.iam.infrastructure.authorization.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.edu.upc.MLTDAH.iam.domain.model.aggregates.User;
import pe.edu.upc.MLTDAH.iam.infrastructure.authorization.model.UserDetailsImplementation;
import pe.edu.upc.MLTDAH.iam.infrastructure.persistence.jpa.UserRepository;

@Service(value = "defaultUserDetailsService")
public class UserDetailsServiceImplementation implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
        return UserDetailsImplementation.build(user);
    }
}
