package pe.edu.upc.MLTDAH.students.domain.model.entities;

import jakarta.persistence.*;
import lombok.*;
import pe.edu.upc.MLTDAH.students.domain.model.valueobjects.SchoolGrades;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@With
public class SchoolGrade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    @Getter
    private SchoolGrades schoolGrade;

    public SchoolGrade(SchoolGrades schoolGrade) {
        this.schoolGrade = schoolGrade;
    }

    public String getStringName() {
        return schoolGrade.getLabel();
    }
}
