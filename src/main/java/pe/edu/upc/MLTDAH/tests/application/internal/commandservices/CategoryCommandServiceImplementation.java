package pe.edu.upc.MLTDAH.tests.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.MLTDAH.tests.domain.model.commands.CreateCategoryCommand;
import pe.edu.upc.MLTDAH.tests.domain.model.commands.DeleteCategoryCommand;
import pe.edu.upc.MLTDAH.tests.domain.model.commands.UpdateCategoryCommand;
import pe.edu.upc.MLTDAH.tests.domain.model.entities.Category;
import pe.edu.upc.MLTDAH.tests.domain.services.CategoryCommandService;
import pe.edu.upc.MLTDAH.tests.infrastructure.persistence.jpa.CategoryRepository;

import java.util.Optional;

@Service
public class CategoryCommandServiceImplementation implements CategoryCommandService {
    private final CategoryRepository categoryRepository;

    public CategoryCommandServiceImplementation(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Optional<Category> handle(CreateCategoryCommand command) {
        Category category = new Category(command);

        var categorySaved = categoryRepository.save(category);

        return Optional.of(categorySaved);
    }

    @Override
    public Optional<Category> handle(UpdateCategoryCommand command, Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Category not found"));

        category.setName(command.name());
        category.setDescription(command.description());

        var categorySaved = categoryRepository.save(category);

        return Optional.of(categorySaved);
    }

    @Override
    public Optional<Category> handle(DeleteCategoryCommand command) {
        Category category = categoryRepository.findById(command.id()).orElseThrow(() -> new IllegalArgumentException("Category not found"));

        this.categoryRepository.delete(category);

        return Optional.of(category);
    }
}
