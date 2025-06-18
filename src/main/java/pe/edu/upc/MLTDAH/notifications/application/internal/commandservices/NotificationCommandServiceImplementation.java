package pe.edu.upc.MLTDAH.notifications.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.MLTDAH.iam.domain.model.aggregates.Institution;
import pe.edu.upc.MLTDAH.iam.infrastructure.persistence.jpa.InstitutionRepository;
import pe.edu.upc.MLTDAH.notifications.domain.model.aggregates.Notification;
import pe.edu.upc.MLTDAH.notifications.domain.model.commands.CreateNotificationCommand;
import pe.edu.upc.MLTDAH.notifications.domain.model.commands.DeleteNotificationCommand;
import pe.edu.upc.MLTDAH.notifications.domain.model.commands.UpdateNotificationCommand;
import pe.edu.upc.MLTDAH.notifications.domain.model.valueobjects.TypeNotification;
import pe.edu.upc.MLTDAH.notifications.domain.services.NotificationCommandService;
import pe.edu.upc.MLTDAH.notifications.infrastructure.persistence.jpa.NotificationRepository;

import java.util.Optional;

@Service
public class NotificationCommandServiceImplementation  implements NotificationCommandService {
    private final NotificationRepository notificationRepository;
    private final InstitutionRepository institutionRepository;

    public NotificationCommandServiceImplementation(NotificationRepository notificationRepository, InstitutionRepository institutionRepository) {
        this.notificationRepository = notificationRepository;
        this.institutionRepository = institutionRepository;
    }

    @Override
    public Optional<Notification> handle(CreateNotificationCommand command) {
        Institution institution = this.institutionRepository.findById(command.institutionId()).orElseThrow(() -> new IllegalArgumentException("Institution not found"));
        TypeNotification typeNotification = TypeNotification.valueOf(command.type());

        Notification notification = new Notification(command, institution, typeNotification);

        var notificationSaved = this.notificationRepository.save(notification);

        return Optional.of(notificationSaved);
    }

    @Override
    public Optional<Notification> handle(UpdateNotificationCommand command, Long id) {
        Notification notification = this.notificationRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Notification not found"));

        notification.setTitle(command.title());
        notification.setDescription(command.description());
        notification.setTag(command.tag());

        var notificationUpdated = this.notificationRepository.save(notification);

        return Optional.of(notificationUpdated);
    }

    @Override
    public Optional<Notification> handle(DeleteNotificationCommand command) {
        Notification notification = this.notificationRepository.findById(command.id()).orElseThrow(() -> new IllegalArgumentException("Notification not found"));

        this.notificationRepository.delete(notification);

        return Optional.of(notification);
    }
}
