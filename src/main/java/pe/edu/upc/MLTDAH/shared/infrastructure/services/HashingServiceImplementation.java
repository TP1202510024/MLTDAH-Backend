package pe.edu.upc.MLTDAH.shared.infrastructure.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.upc.MLTDAH.iam.application.internal.outboundservices.HashingService;

@Service
public class HashingServiceImplementation implements HashingService {
    private final BCryptPasswordEncoder passwordEncoder;

    public HashingServiceImplementation() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return this.passwordEncoder.encode(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
