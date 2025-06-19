package pe.edu.upc.MLTDAH.iam.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.MLTDAH.iam.domain.model.aggregates.Institution;
import pe.edu.upc.MLTDAH.iam.domain.model.commands.CreateInstitutionCommand;
import pe.edu.upc.MLTDAH.iam.domain.model.commands.UpdateInstitutionCommand;
import pe.edu.upc.MLTDAH.iam.domain.model.commands.UploadInstitutionImageCommand;
import pe.edu.upc.MLTDAH.iam.domain.services.InstitutionCommandService;
import pe.edu.upc.MLTDAH.iam.infrastructure.persistence.jpa.InstitutionRepository;
import pe.edu.upc.MLTDAH.uploads.application.internal.outboundservices.S3Service;
import software.amazon.awssdk.services.s3.S3Client;

import java.io.IOException;
import java.util.Optional;

@Service
public class InstitutionCommandServiceImplementation implements InstitutionCommandService {
    private final InstitutionRepository institutionRepository;
    private final S3Service s3Service;

    public InstitutionCommandServiceImplementation(InstitutionRepository institutionRepository, S3Service s3Service) {
        this.institutionRepository = institutionRepository;
        this.s3Service = s3Service;
    }

    @Override
    public Optional<Institution> handle(CreateInstitutionCommand command) {
        Institution institution = new Institution(command);

        var institutionSaved = institutionRepository.save(institution);

        return Optional.of(institutionSaved);
    }

    @Override
    public Optional<Institution> handle(UpdateInstitutionCommand command, Long id) {
        Institution institutionExisting = institutionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Institution not found"));

        institutionExisting.setAddress(command.address());
        institutionExisting.setName(command.name());
        institutionExisting.setCreationDate(command.creationDate());

        var institutionSaved = institutionRepository.save(institutionExisting);

        return Optional.of(institutionSaved);
    }

    @Override
    public Optional<Institution> handle(UploadInstitutionImageCommand command, Long id) throws IOException {
        Institution institutionExisting = institutionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Institution not found"));

        String userImageUrl = this.s3Service.uploadFile(command.file());
        institutionExisting.setPhoto(userImageUrl);

        var institutionSaved = institutionRepository.save(institutionExisting);

        return Optional.of(institutionSaved);
    }
}
