package pe.edu.upc.MLTDAH.iam.interfaces.rest.resources;

import java.util.Date;

public record UpdateInstitutionResource(String name, Date creationDate, String address, String photo) {
    public UpdateInstitutionResource {
        if(name == null || name.isEmpty()) {
            throw new NullPointerException("name is null or empty");
        }
        if(creationDate == null) {
            throw new NullPointerException("creationDate is null");
        }
        if(address == null  || address.isEmpty()) {
            throw new NullPointerException("address is null or empty");
        }
        if(photo == null  || photo.isEmpty()) {
            throw new NullPointerException("photo is null or empty");
        }
    }
}
