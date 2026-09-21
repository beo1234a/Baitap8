package anhtuan.vn.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import anhtuan.vn.entity.Category;
import anhtuan.vn.entity.Product;
import anhtuan.vn.entity.User;
import anhtuan.vn.repository.CategoryRepository;
import anhtuan.vn.repository.ProductRepository;
import anhtuan.vn.repository.UserRepository;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public ProductService(
            ProductRepository productRepository,
            CategoryRepository categoryRepository,
            UserRepository userRepository) {

        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public List<Product> findAllByPriceAsc() {
        return productRepository.findAllByOrderByPriceAsc();
    }

    public List<Product> findByCategory(Long categoryId) {
        return productRepository.findByCategoryIdOrderByPriceAsc(categoryId);
    }

    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Không tìm thấy product có id = " + id));
    }

    public Product create(
            String title,
            Integer quantity,
            String desc,
            Double price,
            Long userId,
            Long categoryId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException(
                        "Không tìm thấy user có id = " + userId));

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException(
                        "Không tìm thấy category có id = " + categoryId));

        Product product = new Product();

        product.setTitle(title);
        product.setQuantity(quantity);
        product.setDesc(desc);
        product.setPrice(price);
        product.setUser(user);
        product.setCategory(category);

        return productRepository.save(product);
    }

    public Product update(
            Long id,
            String title,
            Integer quantity,
            String desc,
            Double price,
            Long userId,
            Long categoryId) {

        Product product = findById(id);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException(
                        "Không tìm thấy user có id = " + userId));

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException(
                        "Không tìm thấy category có id = " + categoryId));

        product.setTitle(title);
        product.setQuantity(quantity);
        product.setDesc(desc);
        product.setPrice(price);
        product.setUser(user);
        product.setCategory(category);

        return productRepository.save(product);
    }

    public Boolean delete(Long id) {

        if (!productRepository.existsById(id)) {
            return false;
        }

        productRepository.deleteById(id);

        return true;
    }
}