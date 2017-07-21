package xzj.service;

import xzj.model.BookNameRule;

import java.util.Map;

/**
 * @Author xzj
 * @Date 2017/7/19.
 */
public interface BookNameService {
    BookNameRule getBookNameRule(Long id);

    Map<String, String> getBookName(Long productId);
}
