package pe.edu.upc.MLTDAH.notifications.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import pe.edu.upc.MLTDAH.iam.domain.model.aggregates.Institution;
import pe.edu.upc.MLTDAH.notifications.domain.model.valueobjects.TypeNotification;
import pe.edu.upc.MLTDAH.students.domain.model.aggregates.Student;

import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Notification extends AbstractAggregateRoot<Notification> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(nullable = false)
    @Getter
    @Setter
    private String title;

    @Column(nullable = false)
    @Getter
    @Setter
    private String description;

    @Column(nullable = false)
    @Getter
    @Setter
    private String tag;

    @Column(nullable = false)
    @Getter
    @Setter
    private Long entityId;

    @ManyToOne
    @JoinColumn(name = "institution_id")
    @Getter
    @Setter
    private Institution institution;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private TypeNotification type;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    @Getter
    private Date createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updatedAt;

    protected  Notification(){
        
    }
}
