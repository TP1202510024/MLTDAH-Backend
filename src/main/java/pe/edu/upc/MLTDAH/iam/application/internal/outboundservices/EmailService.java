package pe.edu.upc.MLTDAH.iam.application.internal.outboundservices;

public interface EmailService {
    void sendRestartCodeEmail(String toEmail, String restartCode);
}
