package xzj.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author xzj
 * @Date 2017/7/19.
 */
@Entity
@Table(name = "title_rule")
public class TitleRule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
