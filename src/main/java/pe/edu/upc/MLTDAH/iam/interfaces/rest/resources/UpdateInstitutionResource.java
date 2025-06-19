package pe.edu.upc.MLTDAH.iam.interfaces.rest.resources;

import java.util.Date;

public record UpdateInstitutionResource(String name, Date creationDate, String address) {
    public UpdateInstitutionResource {
        if(name == null || name.isEmpty()) {
            throw new IllegalArgumentException("name is null or empty");
        }
        if(creationDate == null) {
            throw new IllegalArgumentException("creationDate is null");
        }
        if(address == null  || address.isEmpty()) {
            throw new IllegalArgumentException("address is null or empty");
        }
    }
}
