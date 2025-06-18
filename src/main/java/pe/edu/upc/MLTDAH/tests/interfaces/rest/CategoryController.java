package pe.edu.upc.MLTDAH.tests.interfaces.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.MLTDAH.tests.domain.model.commands.DeleteCategoryCommand;
import pe.edu.upc.MLTDAH.tests.domain.model.entities.Category;
import pe.edu.upc.MLTDAH.tests.domain.model.queries.GetAllCategoriesQuery;
import pe.edu.upc.MLTDAH.tests.domain.model.queries.GetCategoryByIdQuery;
import pe.edu.upc.MLTDAH.tests.domain.services.CategoryCommandService;
import pe.edu.upc.MLTDAH.tests.domain.services.CategoryQueryService;
import pe.edu.upc.MLTDAH.tests.interfaces.rest.resources.CreateCategoryResource;
import pe.edu.upc.MLTDAH.tests.interfaces.rest.resources.UpdateCategoryResource;
import pe.edu.upc.MLTDAH.tests.interfaces.rest.transform.CategoryResourceFromEntityAssembler;
import pe.edu.upc.MLTDAH.tests.interfaces.rest.transform.CreateCategoryCommandFromResourceAssembler;
import pe.edu.upc.MLTDAH.tests.interfaces.rest.transform.UpdateCategoryCommandFromResourceAssembler;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {
    private final CategoryCommandService categoryCommandService;
    private final CategoryQueryService categoryQueryService;

    public CategoryController(CategoryCommandService categoryCommandService, CategoryQueryService categoryQueryService) {
        this.categoryCommandService = categoryCommandService;
        this.categoryQueryService = categoryQueryService;
    }

    @PostMapping
    public ResponseEntity<?> addCategory(@RequestBody CreateCategoryResource createCategoryResource) {
        try {
            Optional<Category> category = categoryCommandService.handle(CreateCategoryCommandFromResourceAssembler.toCommandFromResource(createCategoryResource));

            if (category.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", "Failed to register category"));
            }

            return category.map(source -> ResponseEntity.ok(CategoryResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", ex.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllCategories() {
        try {
            List<Category> categories = categoryQueryService.handle(new GetAllCategoriesQuery());

            if (categories.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }

            return ResponseEntity.ok(categories.stream().map(CategoryResourceFromEntityAssembler::toResourceFromEntity).toList());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", ex.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategory(@PathVariable Long id) {
        try {
            Optional<Category> category = categoryQueryService.handle(new GetCategoryByIdQuery(id));

            if (category.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }

            return category.map(source -> ResponseEntity.ok(CategoryResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", ex.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody UpdateCategoryResource updateCategoryResource) {
        try {
            Optional<Category> category =  categoryCommandService.handle(UpdateCategoryCommandFromResourceAssembler.toCommandFromResource(updateCategoryResource), id);

            if (category.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", "Failed to updated category"));
            }

            return category.map(source -> ResponseEntity.ok(CategoryResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", ex.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        try {
            Optional<Category> category = categoryCommandService.handle(new DeleteCategoryCommand(id));

            if(category.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }

            return category.map(source -> ResponseEntity.ok(CategoryResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", ex.getMessage()));
        }
    }
}
