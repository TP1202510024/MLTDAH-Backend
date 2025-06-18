package pe.edu.upc.MLTDAH.notifications.interfaces.rest.resource;

import pe.edu.upc.MLTDAH.iam.interfaces.rest.resources.InstitutionResource;
import pe.edu.upc.MLTDAH.notifications.domain.model.valueobjects.TypeNotification;

public record NotificationResource(Long id, String title, String description, String tag, Long entityId, InstitutionResource institution, TypeNotification type) {
}
