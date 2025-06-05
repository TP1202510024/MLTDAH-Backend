package pe.edu.upc.MLTDAH.iam.interfaces.rest.transform;

import pe.edu.upc.MLTDAH.iam.domain.model.aggregates.User;
import pe.edu.upc.MLTDAH.iam.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User entity) {
        return new UserResource(entity.getId(), entity.getFirstName(), entity.getLastName(), entity.getDni(), entity.getBirthDate(), entity.getPhoto(), entity.getEmail(), entity.getPassword(), InstitutionResourceFromEntityAssembler.toResourceFromEntity(entity.getInstitution()), RoleResourceFormEntityAssembler.toResourceFromEntity(entity.getRole()));
    }
}
