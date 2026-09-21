package anhtuan.vn.controller;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import anhtuan.vn.dto.ProductInput;
import anhtuan.vn.entity.Product;
import anhtuan.vn.service.ProductService;

@Controller
public class ProductGraphQLController {

    private final ProductService productService;

    public ProductGraphQLController(ProductService productService) {
        this.productService = productService;
    }

    @QueryMapping
    public List<Product> products() {
        return productService.findAll();
    }

    @QueryMapping
    public List<Product> productsByPriceAsc() {
        return productService.findAllByPriceAsc();
    }

    @QueryMapping
    public List<Product> productsByCategory(@Argument Long categoryId) {
        return productService.findByCategory(categoryId);
    }

    @QueryMapping
    public Product product(@Argument Long id) {
        return productService.findById(id);
    }

    @MutationMapping
    public Product createProduct(@Argument ProductInput input) {

        return productService.create(
                input.getTitle(),
                input.getQuantity(),
                input.getDesc(),
                input.getPrice(),
                input.getUserId(),
                input.getCategoryId()
        );
    }

    @MutationMapping
    public Product updateProduct(
            @Argument Long id,
            @Argument ProductInput input) {

        return productService.update(
                id,
                input.getTitle(),
                input.getQuantity(),
                input.getDesc(),
                input.getPrice(),
                input.getUserId(),
                input.getCategoryId()
        );
    }

    @MutationMapping
    public Boolean deleteProduct(@Argument Long id) {
        return productService.delete(id);
    }
}