package pe.edu.upc.MLTDAH.iam.interfaces.rest.transform;

import pe.edu.upc.MLTDAH.iam.domain.model.aggregates.Institution;
import pe.edu.upc.MLTDAH.iam.interfaces.rest.resources.InstitutionResource;

public class InstitutionResourceFromEntityAssembler {
    public static InstitutionResource toResourceFromEntity(Institution institution) {
        return new InstitutionResource(institution.getId(), institution.getName(), institution.getCreationDate(), institution.getAddress(), institution.getPhoto(), institution.getCreatedAt(), institution.getUpdatedAt());
    }
}
