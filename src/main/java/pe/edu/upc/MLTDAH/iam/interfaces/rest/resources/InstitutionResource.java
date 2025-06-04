package pe.edu.upc.MLTDAH.iam.interfaces.rest.resources;

import java.util.Date;

public record InstitutionResource(Long id, String name, Date creationDate, String address, String photo, Date createdAt, Date updatedAt) {
    public InstitutionResource {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        if(name == null) {
            throw new IllegalArgumentException("name is null");
        }
        if(creationDate == null) {
            throw new IllegalArgumentException("creationDate is null");
        }
        if(address == null) {
            throw new IllegalArgumentException("address is null");
        }
        if(photo == null) {
            throw new IllegalArgumentException("photo is null");
        }
        if(createdAt == null) {
            throw new IllegalArgumentException("createdAt is null");
        }
        if(updatedAt == null) {
            throw new IllegalArgumentException("updatedAt is null");
        }
    }
}
