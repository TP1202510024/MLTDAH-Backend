package pe.edu.upc.MLTDAH.tests.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.MLTDAH.tests.domain.model.entities.Category;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAll();
    Optional<Category> findById(Long id);
}
