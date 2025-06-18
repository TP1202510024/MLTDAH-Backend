package pe.edu.upc.MLTDAH.tests.domain.services;

import pe.edu.upc.MLTDAH.tests.domain.model.commands.CreateCategoryCommand;
import pe.edu.upc.MLTDAH.tests.domain.model.commands.DeleteCategoryCommand;
import pe.edu.upc.MLTDAH.tests.domain.model.commands.UpdateCategoryCommand;
import pe.edu.upc.MLTDAH.tests.domain.model.entities.Category;

import java.util.Optional;

public interface CategoryCommandService {
    Optional<Category> handle(CreateCategoryCommand command);
    Optional<Category> handle(UpdateCategoryCommand command, Long id);
    Optional<Category> handle(DeleteCategoryCommand command);
}
