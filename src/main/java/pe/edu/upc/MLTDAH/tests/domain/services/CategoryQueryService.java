package pe.edu.upc.MLTDAH.tests.domain.services;

import pe.edu.upc.MLTDAH.tests.domain.model.entities.Category;
import pe.edu.upc.MLTDAH.tests.domain.model.queries.GetAllCategoriesQuery;
import pe.edu.upc.MLTDAH.tests.domain.model.queries.GetCategoryByIdQuery;

import java.util.List;
import java.util.Optional;

public interface CategoryQueryService {
    List<Category> handle(GetAllCategoriesQuery query);
    Optional<Category> handle(GetCategoryByIdQuery query);
}
