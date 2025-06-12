package pe.edu.upc.MLTDAH.students.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.MLTDAH.iam.domain.model.aggregates.User;

@Entity
public class Parent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @OneToOne(optional = false)
    @Getter
    @Setter
    private Student student;

    @OneToOne(optional = false)
    @Getter
    @Setter
    private User parent;

    public Parent(User parent, Student student) {
        setParent(parent);
        setStudent(student);
    }
}

