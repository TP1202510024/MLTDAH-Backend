package pe.edu.upc.MLTDAH.tests.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import pe.edu.upc.MLTDAH.tests.domain.model.commands.CreateExamCommand;
import pe.edu.upc.MLTDAH.tests.domain.model.entities.Question;

import java.util.Date;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Exam extends AbstractAggregateRoot<Exam> {
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

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL)
    @Getter
    private List<Question> questions;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    @Getter
    private Date createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updatedAt;

    public Exam(CreateExamCommand command) {
        setTitle(command.title());
        setDescription(command.description());
    }

    protected Exam() {

    }
}
