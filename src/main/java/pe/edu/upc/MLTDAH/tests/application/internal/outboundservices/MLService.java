package pe.edu.upc.MLTDAH.tests.application.internal.outboundservices;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pe.edu.upc.MLTDAH.tests.domain.model.dto.AnswerDTO;
import pe.edu.upc.MLTDAH.tests.domain.model.dto.MLResultDTO;
import pe.edu.upc.MLTDAH.tests.domain.model.entities.Answer;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class MLService {
    private final WebClient webClient;

    public MLService(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("http://localhost:5000").build();
    }

    public MLResultDTO sendTest(List<AnswerDTO> request) {
        return webClient.post()
                .uri("/api/tdah/model")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(MLResultDTO.class)
                .block();
    }
}
