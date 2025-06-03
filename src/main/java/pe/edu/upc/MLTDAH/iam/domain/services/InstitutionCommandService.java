package pe.edu.upc.MLTDAH.iam.domain.services;

import pe.edu.upc.MLTDAH.iam.domain.model.aggregates.Institution;
import pe.edu.upc.MLTDAH.iam.domain.model.commands.CreateInstitutionCommand;
import pe.edu.upc.MLTDAH.iam.domain.model.commands.UpdateInstitutionCommand;

import java.util.Optional;

public interface InstitutionCommandService {
    Optional<Institution> handle(CreateInstitutionCommand command);
    Optional<Institution> handle(UpdateInstitutionCommand command, Long id);
}
