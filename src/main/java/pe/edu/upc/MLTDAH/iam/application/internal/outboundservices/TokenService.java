package pe.edu.upc.MLTDAH.iam.application.internal.outboundservices;

public interface TokenService {

    String generateToken(String email);
    String getEmailFromToken(String token);
    boolean validateToken(String token);
}
