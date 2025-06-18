package pe.edu.upc.MLTDAH.tests.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import pe.edu.upc.MLTDAH.students.domain.model.aggregates.Student;
import pe.edu.upc.MLTDAH.tests.domain.model.commands.CreateExamCommand;
import pe.edu.upc.MLTDAH.tests.domain.model.commands.CreateTestCommand;
import pe.edu.upc.MLTDAH.tests.domain.model.entities.Answer;
import pe.edu.upc.MLTDAH.tests.domain.model.entities.Category;
import pe.edu.upc.MLTDAH.tests.domain.model.entities.Question;

import java.util.Date;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Test extends AbstractAggregateRoot<Test> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @Getter
    @Setter
    private Student student;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    @Getter
    @Setter
    private Exam exam;

    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL, orphanRemoval = true)
    @Getter
    private List<Answer> answers;

    @Column(nullable = false)
    @Getter
    @Setter
    private Double probability;

    @Column(nullable = false)
    @Getter
    @Setter
    private String result;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    @Getter
    private Date createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updatedAt;

    public Test(CreateTestCommand command, Student student, Exam exam) {
        setStudent(student);
        setExam(exam);
    }

    protected Test() {

    }
}
