package pe.edu.upc.MLTDAH.notifications.interfaces.rest.resource;

public record UpdateNotificationResource(String title, String description, String tag) {
    public UpdateNotificationResource {
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
