package pe.edu.upc.MLTDAH.notifications.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.MLTDAH.notifications.domain.model.aggregates.Notification;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findAll();
    List<Notification> findByInstitutionId(Long institutionId);
}
