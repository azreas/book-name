package xzj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xzj.model.Product;

/**
 * @Author xzj
 * @Date 2017/7/20.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findById(Long id);
}
