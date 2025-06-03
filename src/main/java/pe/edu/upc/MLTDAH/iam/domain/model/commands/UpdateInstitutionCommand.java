package pe.edu.upc.MLTDAH.iam.domain.model.commands;

import java.util.Date;

public record UpdateInstitutionCommand(String name, Date creationDate, String address, String photo) {
    public UpdateInstitutionCommand {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name cannot be null or empty");
        }
        if (creationDate == null) {
            throw new IllegalArgumentException("creationDate cannot be null");
        }
        if (address == null || address.isBlank()) {
            throw new IllegalArgumentException("address cannot be null or empty");
        }
        if (photo == null || photo.isBlank()) {
            throw new IllegalArgumentException("photo cannot be null");
        }
    }
}
