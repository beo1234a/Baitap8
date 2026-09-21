package anhtuan.vn.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import anhtuan.vn.entity.Category;
import anhtuan.vn.entity.User;
import anhtuan.vn.repository.CategoryRepository;
import anhtuan.vn.repository.UserRepository;

@Service
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public CategoryService(
            CategoryRepository categoryRepository,
            UserRepository userRepository) {

        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Không tìm thấy category có id = " + id));
    }

    public Category create(
            String name,
            String images,
            List<Long> userIds) {

        Category category = new Category();

        category.setName(name);
        category.setImages(images);

        Set<User> users = new HashSet<>();

        if (userIds != null) {
            users.addAll(userRepository.findAllById(userIds));
        }

        category.setUsers(users);

        return categoryRepository.save(category);
    }

    public Category update(
            Long id,
            String name,
            String images,
            List<Long> userIds) {

        Category category = findById(id);

        category.setName(name);
        category.setImages(images);

        Set<User> users = new HashSet<>();

        if (userIds != null) {
            users.addAll(userRepository.findAllById(userIds));
        }

        category.setUsers(users);

        return categoryRepository.save(category);
    }

    public Boolean delete(Long id) {

        if (!categoryRepository.existsById(id)) {
            return false;
        }

        categoryRepository.deleteById(id);

        return true;
    }
}