package pe.edu.upc.MLTDAH.notifications.domain.model.commands;

public record UpdateNotificationCommand(String title, String description, String tag) {
    public UpdateNotificationCommand {
        if(title == null || title.isEmpty()) {
            throw new IllegalArgumentException("title cannot be null or empty");
        }
        if(description == null || description.isEmpty()) {
            throw new IllegalArgumentException("description cannot be null or empty");
        }
        if(tag == null || tag.isEmpty()) {
            throw new IllegalArgumentException("tag cannot be null or empty");
        }
    }
}
