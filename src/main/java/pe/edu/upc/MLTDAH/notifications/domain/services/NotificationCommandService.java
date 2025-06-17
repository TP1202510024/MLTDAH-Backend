package pe.edu.upc.MLTDAH.notifications.domain.services;

import pe.edu.upc.MLTDAH.notifications.domain.model.aggregates.Notification;
import pe.edu.upc.MLTDAH.notifications.domain.model.commands.CreateNotificationCommand;
import pe.edu.upc.MLTDAH.notifications.domain.model.commands.DeleteNotificationCommand;
import pe.edu.upc.MLTDAH.notifications.domain.model.commands.UpdateNotificationCommand;

import java.util.Optional;

public interface NotificationCommandService {
    Optional<Notification> handle(CreateNotificationCommand command);
    Optional<Notification> handle(UpdateNotificationCommand command, Long id);
    Optional<Notification> handle(DeleteNotificationCommand command);
}
