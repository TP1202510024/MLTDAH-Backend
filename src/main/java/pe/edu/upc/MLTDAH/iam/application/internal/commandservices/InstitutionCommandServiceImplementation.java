package pe.edu.upc.MLTDAH.iam.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.MLTDAH.iam.domain.model.aggregates.Institution;
import pe.edu.upc.MLTDAH.iam.domain.model.commands.CreateInstitutionCommand;
import pe.edu.upc.MLTDAH.iam.domain.model.commands.UpdateInstitutionCommand;
import pe.edu.upc.MLTDAH.iam.domain.services.InstitutionCommandService;
import pe.edu.upc.MLTDAH.iam.infrastructure.persistence.jpa.InstitutionRepository;

import java.util.Optional;

@Service
public class InstitutionCommandServiceImplementation implements InstitutionCommandService {
    private final InstitutionRepository institutionRepository;

    public InstitutionCommandServiceImplementation(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
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
        institutionExisting.setPhoto(command.photo());
        institutionExisting.setCreationDate(command.creationDate());

        var institutionSaved = institutionRepository.save(institutionExisting);

        return Optional.of(institutionSaved);
    }
}
