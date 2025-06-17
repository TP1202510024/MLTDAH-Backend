package pe.edu.upc.MLTDAH.notifications.domain.services;

import pe.edu.upc.MLTDAH.notifications.domain.model.aggregates.Notification;
import pe.edu.upc.MLTDAH.notifications.domain.model.queries.GetAllNotificationsByInstitutionIdQuery;
import pe.edu.upc.MLTDAH.notifications.domain.model.queries.GetAllNotificationsQuery;

import java.util.List;

public interface NotificationQueryService {
    List<Notification> handle(GetAllNotificationsQuery query);
    List<Notification> handle(GetAllNotificationsByInstitutionIdQuery query);
}
