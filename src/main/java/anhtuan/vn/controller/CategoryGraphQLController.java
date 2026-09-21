package anhtuan.vn.controller;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import anhtuan.vn.dto.CategoryInput;
import anhtuan.vn.entity.Category;
import anhtuan.vn.entity.User;
import anhtuan.vn.repository.UserRepository;
import anhtuan.vn.service.CategoryService;

@Controller
public class CategoryGraphQLController {

    private final CategoryService categoryService;
    private final UserRepository userRepository;

    public CategoryGraphQLController(
            CategoryService categoryService,
            UserRepository userRepository) {

        this.categoryService = categoryService;
        this.userRepository = userRepository;
    }

    @QueryMapping
    public List<Category> categories() {
        return categoryService.findAll();
    }

    @QueryMapping
    public Category category(@Argument Long id) {
        return categoryService.findById(id);
    }

    @QueryMapping
    public List<User> users() {
        return userRepository.findAll();
    }

    @MutationMapping
    public Category createCategory(@Argument CategoryInput input) {

        return categoryService.create(
                input.getName(),
                input.getImages(),
                input.getUserIds()
        );
    }

    @MutationMapping
    public Category updateCategory(
            @Argument Long id,
            @Argument CategoryInput input) {

        return categoryService.update(
                id,
                input.getName(),
                input.getImages(),
                input.getUserIds()
        );
    }

    @MutationMapping
    public Boolean deleteCategory(@Argument Long id) {
        return categoryService.delete(id);
    }
}