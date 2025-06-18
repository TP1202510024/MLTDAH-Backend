package pe.edu.upc.MLTDAH.tests.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import pe.edu.upc.MLTDAH.tests.domain.model.aggregates.Exam;
import pe.edu.upc.MLTDAH.tests.domain.model.commands.CreateQuestionCommand;

import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Question extends AbstractAggregateRoot<Question> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(nullable = false)
    @Getter
    @Setter
    private String text;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @Getter
    @Setter
    private Category category;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    @Getter
    @Setter
    private Exam exam;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    @Getter
    private Date createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updatedAt;

    public Question(CreateQuestionCommand command, Category category, Exam exam) {
        setText(command.text());
        setCategory(category);
        setExam(exam);
    }

    protected Question() {

    }
}
