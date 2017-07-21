package xzj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xzj.model.Metadata;

/**
 * @Author xzj
 * @Date 2017/7/19.
 */
public interface TitleRepository extends JpaRepository<Metadata,Long> {
}
