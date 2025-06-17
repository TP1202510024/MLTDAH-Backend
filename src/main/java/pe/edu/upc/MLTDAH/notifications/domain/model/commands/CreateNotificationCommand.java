package pe.edu.upc.MLTDAH.notifications.domain.model.commands;

public record CreateNotificationCommand(String title, String description, String tag, Long entityId, Long institutionId, String type) {
    public CreateNotificationCommand {
        if(title == null || title.isEmpty()) {
            throw new IllegalArgumentException("title cannot be null or empty");
        }
        if(description == null || description.isEmpty()) {
            throw new IllegalArgumentException("description cannot be null or empty");
        }
        if(tag == null || tag.isEmpty()) {
            throw new IllegalArgumentException("tag cannot be null or empty");
        }
        if(entityId == null || entityId < 0) {
            throw new IllegalArgumentException("entityId cannot be null or negative");
        }
        if (institutionId == null || institutionId < 0) {
            throw new IllegalArgumentException("institutionId cannot be null or negative");
        }
        if(type == null || type.isEmpty()) {
            throw new IllegalArgumentException("type cannot be null or empty");
        }
    }
}
