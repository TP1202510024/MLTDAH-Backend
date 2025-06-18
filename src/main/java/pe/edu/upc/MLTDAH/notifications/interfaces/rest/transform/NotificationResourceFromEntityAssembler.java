package pe.edu.upc.MLTDAH.notifications.interfaces.rest.transform;

import pe.edu.upc.MLTDAH.iam.interfaces.rest.transform.InstitutionResourceFromEntityAssembler;
import pe.edu.upc.MLTDAH.notifications.domain.model.aggregates.Notification;
import pe.edu.upc.MLTDAH.notifications.interfaces.rest.resource.NotificationResource;

public class NotificationResourceFromEntityAssembler {
    public static NotificationResource toResourceFromEntity(Notification entity) {
        return new NotificationResource(entity.getId(), entity.getTitle(), entity.getDescription(), entity.getTag(), entity.getEntityId(), InstitutionResourceFromEntityAssembler.toResourceFromEntity(entity.getInstitution()), entity.getType());
    }
}
