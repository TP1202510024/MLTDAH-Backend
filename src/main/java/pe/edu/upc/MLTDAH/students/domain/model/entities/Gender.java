package pe.edu.upc.MLTDAH.students.domain.model.entities;

import jakarta.persistence.*;
import lombok.*;
import pe.edu.upc.MLTDAH.students.domain.model.valueobjects.Genders;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@With
public class Gender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    @Getter
    private Genders name;

    public Gender(Genders name) {
        this.name = name;
    }

    public String getStringName() {
        return name.getLabel();
    }
}
