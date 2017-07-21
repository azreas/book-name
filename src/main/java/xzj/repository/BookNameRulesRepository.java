package xzj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xzj.model.BookNameRule;

/**
 * @Author xzj
 * @Date 2017/7/19.
 */
public interface BookNameRulesRepository extends JpaRepository<BookNameRule, Long> {

    BookNameRule findById(Long id);
}
