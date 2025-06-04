package pe.edu.upc.MLTDAH.iam.interfaces.rest.resources;

import java.util.Date;

public record InstitutionResource(Long id, String name, Date creationDate, String address, String photo, Date createdAt, Date updatedAt) {
    public InstitutionResource {
        if (id == null) {
            throw new NullPointerException("id is null");
        }
        if(name == null) {
            throw new NullPointerException("name is null");
        }
        if(creationDate == null) {
            throw new NullPointerException("creationDate is null");
        }
        if(address == null) {
            throw new NullPointerException("address is null");
        }
        if(photo == null) {
            throw new NullPointerException("photo is null");
        }
        if(createdAt == null) {
            throw new NullPointerException("createdAt is null");
        }
        if(updatedAt == null) {
            throw new NullPointerException("updatedAt is null");
        }
    }
}
