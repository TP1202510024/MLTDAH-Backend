package pe.edu.upc.MLTDAH.iam.interfaces.rest.transform;

import pe.edu.upc.MLTDAH.iam.domain.model.entities.Role;
import pe.edu.upc.MLTDAH.iam.interfaces.rest.resources.RoleResource;

public class RoleResourceFormEntityAssembler {
    public static RoleResource toResourceFromEntity(Role role) {
        return new RoleResource(role.getId(), role.getStringName());
    }
}
