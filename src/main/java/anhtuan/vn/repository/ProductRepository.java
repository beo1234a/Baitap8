package anhtuan.vn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import anhtuan.vn.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByOrderByPriceAsc();

    List<Product> findByCategoryIdOrderByPriceAsc(Long categoryId);
}