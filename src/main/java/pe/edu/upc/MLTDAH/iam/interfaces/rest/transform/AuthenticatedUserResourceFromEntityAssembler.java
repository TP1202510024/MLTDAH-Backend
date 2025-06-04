package pe.edu.upc.MLTDAH.iam.interfaces.rest.transform;

import pe.edu.upc.MLTDAH.iam.domain.model.aggregates.User;
import pe.edu.upc.MLTDAH.iam.interfaces.rest.resources.AuthenticatedUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {
    public static AuthenticatedUserResource toResourceFromEntity(User entity, String token) {
        return new AuthenticatedUserResource(entity.getFirstName(), entity.getLastName(), entity.getDni(), entity.getBirthDate(), entity.getPhoto(), entity.getEmail(), entity.getPassword(), InstitutionResourceFromEntityAssembler.toResourceFromEntity(entity.getInstitution()), token);
    }
}
