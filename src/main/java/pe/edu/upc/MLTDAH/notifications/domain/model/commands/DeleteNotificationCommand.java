package pe.edu.upc.MLTDAH.notifications.domain.model.commands;

public record DeleteNotificationCommand(Long id) {
    public DeleteNotificationCommand {
        if (id == null || id < 0) {
            throw new IllegalArgumentException("id cannot be null or negative");
        }
    }
}
