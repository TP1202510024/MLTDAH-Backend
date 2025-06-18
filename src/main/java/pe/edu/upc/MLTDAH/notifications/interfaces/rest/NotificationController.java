package pe.edu.upc.MLTDAH.notifications.interfaces.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.MLTDAH.notifications.domain.model.aggregates.Notification;
import pe.edu.upc.MLTDAH.notifications.domain.model.commands.DeleteNotificationCommand;
import pe.edu.upc.MLTDAH.notifications.domain.model.queries.GetAllNotificationsByInstitutionIdQuery;
import pe.edu.upc.MLTDAH.notifications.domain.model.queries.GetAllNotificationsQuery;
import pe.edu.upc.MLTDAH.notifications.domain.services.NotificationCommandService;
import pe.edu.upc.MLTDAH.notifications.domain.services.NotificationQueryService;
import pe.edu.upc.MLTDAH.notifications.interfaces.rest.transform.NotificationResourceFromEntityAssembler;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/notifications")
public class NotificationController {
    private final NotificationCommandService notificationCommandService;
    private final NotificationQueryService notificationQueryService;

    public NotificationController(NotificationCommandService notificationCommandService, NotificationQueryService notificationQueryService) {
        this.notificationCommandService = notificationCommandService;
        this.notificationQueryService = notificationQueryService;
    }

    @GetMapping
    public ResponseEntity<?> getAllNotifications() {
        List<Notification> notifications = notificationQueryService.handle(new GetAllNotificationsQuery());

        if(notifications.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.ok(notifications.stream().map(NotificationResourceFromEntityAssembler::toResourceFromEntity).toList());
    }

    @GetMapping("/institution/{institutionId}")
    public ResponseEntity<?> getAllStudentsByInstitutionId(@PathVariable Long institutionId) {
        List<Notification> notifications = notificationQueryService.handle(new GetAllNotificationsByInstitutionIdQuery(institutionId));

        if(notifications.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.ok(notifications.stream().map(NotificationResourceFromEntityAssembler::toResourceFromEntity).toList());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            Optional<Notification> notification =  notificationCommandService.handle(new DeleteNotificationCommand(id));

            if(notification.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }

            return notification.map(source -> ResponseEntity.ok(NotificationResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", ex.getMessage()));
        }
    }
}
