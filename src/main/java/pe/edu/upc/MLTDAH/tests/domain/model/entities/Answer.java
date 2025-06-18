package pe.edu.upc.MLTDAH.tests.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.MLTDAH.tests.domain.model.aggregates.Test;

@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(nullable = false)
    @Getter
    @Setter
    private String value;

    @ManyToOne
    @JoinColumn(name = "question_id")
    @Getter
    @Setter
    private Question question;

    @ManyToOne
    @JoinColumn(name = "test_id")
    @Getter
    @Setter
    private Test test;

    public Answer() {

    }
}
