package pe.edu.upc.MLTDAH.tests.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.MLTDAH.tests.domain.model.entities.Category;
import pe.edu.upc.MLTDAH.tests.domain.model.queries.GetAllCategoriesQuery;
import pe.edu.upc.MLTDAH.tests.domain.model.queries.GetCategoryByIdQuery;
import pe.edu.upc.MLTDAH.tests.domain.services.CategoryQueryService;
import pe.edu.upc.MLTDAH.tests.infrastructure.persistence.jpa.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryQueryServiceImplementation implements CategoryQueryService {
    private final CategoryRepository categoryRepository;

    public CategoryQueryServiceImplementation(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> handle(GetAllCategoriesQuery query) {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> handle(GetCategoryByIdQuery query) {
        return categoryRepository.findById(query.id());
    }
}
