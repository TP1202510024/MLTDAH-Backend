package pe.edu.upc.MLTDAH.notifications.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.MLTDAH.notifications.domain.model.aggregates.Notification;
import pe.edu.upc.MLTDAH.notifications.domain.model.queries.GetAllNotificationsByInstitutionIdQuery;
import pe.edu.upc.MLTDAH.notifications.domain.model.queries.GetAllNotificationsQuery;
import pe.edu.upc.MLTDAH.notifications.domain.services.NotificationQueryService;
import pe.edu.upc.MLTDAH.notifications.infrastructure.persistence.jpa.NotificationRepository;

import java.util.List;

@Service
public class NotificationQueryServiceImplementation implements NotificationQueryService {
    private final NotificationRepository notificationRepository;

    public NotificationQueryServiceImplementation(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public List<Notification> handle(GetAllNotificationsQuery query) {
        return notificationRepository.findAll();
    }

    @Override
    public List<Notification> handle(GetAllNotificationsByInstitutionIdQuery query) {
        return notificationRepository.findByInstitutionId(query.institutionId());
    }
}
