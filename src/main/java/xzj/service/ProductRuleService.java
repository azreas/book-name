package xzj.service;

import xzj.model.BookNameRule;

import java.util.List;

/**
 * @Author xzj
 * @Date 2017/7/24.
 */
public interface ProductRuleService {
    List<BookNameRule> getBookNameRules(Long productId);


}
