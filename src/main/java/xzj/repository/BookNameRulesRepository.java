package xzj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import xzj.model.BookNameRule;

import java.util.List;

/**
 * @Author xzj
 * @Date 2017/7/19.
 */
public interface BookNameRulesRepository extends JpaRepository<BookNameRule, Long> {

    BookNameRule findById(Long id);

    @Query(value = "SELECT rule.* FROM book_name_rules rule WHERE rule.id in (SELECT p_r._rule FROM product_rule p_r WHERE p_r._product=?)",nativeQuery = true)
    List<BookNameRule> findByProductId(Long productId);
}
