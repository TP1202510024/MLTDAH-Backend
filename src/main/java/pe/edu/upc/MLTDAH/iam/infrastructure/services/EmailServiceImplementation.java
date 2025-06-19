package pe.edu.upc.MLTDAH.iam.infrastructure.services;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pe.edu.upc.MLTDAH.iam.application.internal.outboundservices.EmailService;

@Service
public class EmailServiceImplementation implements EmailService {
    private final Resend resend;
    private final String fromEmail;

    public EmailServiceImplementation(@Value("${resend.api.key}") String apiKey, @Value("${resend.from}") String fromEmail) {
        this.resend = new Resend(apiKey);
        this.fromEmail = fromEmail;
    }

    @Override
    public void sendRestartCodeEmail(String toEmail, String restartCode) {
        CreateEmailOptions params = CreateEmailOptions.builder()
                .from(fromEmail)
                .to(toEmail)
                .subject("Código de recuperación de contraseña")
                .html(String.format("<p>Tu código es: <strong>%s</strong></p>", restartCode))
                .build();

        try {
            CreateEmailResponse data = resend.emails().send(params);
        } catch (ResendException e) {
            throw new RuntimeException("Failed to send mail: ", e);
        }
    }
}
