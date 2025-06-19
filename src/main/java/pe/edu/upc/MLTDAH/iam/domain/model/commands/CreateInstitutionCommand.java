package pe.edu.upc.MLTDAH.iam.domain.model.commands;

import java.util.Date;

public record CreateInstitutionCommand(String name, Date creationDate, String address) {
    public CreateInstitutionCommand {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name cannot be null or empty");
        }
        if (creationDate == null) {
            throw new IllegalArgumentException("creationDate cannot be null");
        }
        if (address == null || address.isBlank()) {
            throw new IllegalArgumentException("address cannot be null or empty");
        }
    }
}
