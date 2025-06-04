package pe.edu.upc.MLTDAH.iam.interfaces.rest.resources;

import java.util.Date;

public record CreateInstitutionResource(String name, Date creationDate, String address, String photoInstitution) {
    public CreateInstitutionResource {
        if(name == null) {
            throw new IllegalArgumentException("name is null");
        }
        if(creationDate == null) {
            throw new IllegalArgumentException("creationDate is null");
        }
        if(address == null) {
            throw new IllegalArgumentException("address is null");
        }
        if(photoInstitution == null) {
            throw new IllegalArgumentException("photo institution is null");
        }
    }
}
