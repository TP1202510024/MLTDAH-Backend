package pe.edu.upc.MLTDAH.iam.application.internal.outboundservices;

public interface HashingService {
    String encode(CharSequence rawPassword);

    boolean matches(CharSequence rawPassword, String encodedPassword);
}
