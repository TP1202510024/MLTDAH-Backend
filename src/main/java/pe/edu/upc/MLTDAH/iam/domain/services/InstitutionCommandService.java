package pe.edu.upc.MLTDAH.iam.domain.services;

import pe.edu.upc.MLTDAH.iam.domain.model.aggregates.Institution;
import pe.edu.upc.MLTDAH.iam.domain.model.commands.CreateInstitutionCommand;
import pe.edu.upc.MLTDAH.iam.domain.model.commands.UpdateInstitutionCommand;
import pe.edu.upc.MLTDAH.iam.domain.model.commands.UploadInstitutionImageCommand;

import java.io.IOException;
import java.util.Optional;

public interface InstitutionCommandService {
    Optional<Institution> handle(CreateInstitutionCommand command);
    Optional<Institution> handle(UpdateInstitutionCommand command, Long id);
    Optional<Institution> handle(UploadInstitutionImageCommand command, Long id) throws IOException;
}
