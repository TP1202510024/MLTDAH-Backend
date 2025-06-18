package pe.edu.upc.MLTDAH.tests.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import pe.edu.upc.MLTDAH.tests.domain.model.commands.CreateCategoryCommand;

import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Category extends AbstractAggregateRoot<Category> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(nullable = false)
    @Getter
    @Setter
    private String name;

    @Column(nullable = false)
    @Getter
    @Setter
    private String description;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    @Getter
    private Date createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    @Getter
    private Date updatedAt;

    public Category(CreateCategoryCommand command) {
        setName(command.name());
        setDescription(command.description());
    }

    protected Category() {

    }
}
