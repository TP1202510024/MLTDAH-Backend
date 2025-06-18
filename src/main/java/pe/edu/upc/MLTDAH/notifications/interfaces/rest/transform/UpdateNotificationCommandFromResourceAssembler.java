package pe.edu.upc.MLTDAH.notifications.interfaces.rest.transform;

import pe.edu.upc.MLTDAH.notifications.domain.model.aggregates.Notification;
import pe.edu.upc.MLTDAH.notifications.domain.model.commands.UpdateNotificationCommand;
import pe.edu.upc.MLTDAH.notifications.interfaces.rest.resource.UpdateNotificationResource;

public class UpdateNotificationCommandFromResourceAssembler {
    public static UpdateNotificationCommand toCommandFromResource(UpdateNotificationResource resource) {
        return new UpdateNotificationCommand(resource.title(), resource.description(), resource.tag());
    }
}
