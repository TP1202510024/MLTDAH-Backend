package pe.edu.upc.MLTDAH.notifications.interfaces.rest.transform;

import pe.edu.upc.MLTDAH.notifications.domain.model.commands.CreateNotificationCommand;
import pe.edu.upc.MLTDAH.notifications.interfaces.rest.resource.CreateNotificationResource;

public class CreateNotificationCommandFromResourceAssembler {
    public static CreateNotificationCommand toCommandFromResource(CreateNotificationResource resource) {
        return new CreateNotificationCommand(resource.title(), resource.description(), resource.tag(), resource.entityId(), resource.institutionId(), resource.type());
    }
}
