package pe.edu.upc.MLTDAH.students.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import pe.edu.upc.MLTDAH.iam.domain.model.aggregates.Institution;
import pe.edu.upc.MLTDAH.students.domain.model.commands.CreateStudentCommand;
import pe.edu.upc.MLTDAH.students.domain.model.entities.Gender;
import pe.edu.upc.MLTDAH.students.domain.model.entities.SchoolGrade;

import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Student extends AbstractAggregateRoot<Student> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(nullable = false)
    @Getter
    @Setter
    private String firstName;

    @Column(nullable = false)
    @Getter
    @Setter
    private String lastName;

    @Column(nullable = false)
    @Getter
    @Setter
    private Date birthDate;

    @Column(nullable = false)
    @Getter
    @Setter
    private String photo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "schoolgrade_id")
    @Getter
    @Setter
    private SchoolGrade schoolGrade;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gender_id")
    @Getter
    @Setter
    private Gender gender;

    @ManyToOne
    @JoinColumn(name = "institution_id")
    @Getter
    @Setter
    private Institution institution;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    @Getter
    private Date createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updatedAt;

    public Student(CreateStudentCommand command, SchoolGrade schoolGrade, Gender gender, Institution institution) {
        setFirstName(command.firstName());
        setLastName(command.lastName());
        setBirthDate(command.birthDate());
        setPhoto(command.photo());
        setSchoolGrade(schoolGrade);
        setGender(gender);
        setInstitution(institution);
    }

    protected Student() {

    }
}