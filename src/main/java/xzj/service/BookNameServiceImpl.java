package xzj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xzj.model.BookNameRule;
import xzj.model.Metadata;
import xzj.model.Product;
import xzj.repository.BookNameRulesRepository;
import xzj.repository.ProductRepository;
import xzj.wrapper.ProductWrapper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author xzj
 * @Date 2017/7/19.
 */
@Service
public class BookNameServiceImpl implements BookNameService {

    @Autowired
    BookNameRulesRepository bookNameRulesRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    RecommendService recommendService;

    @Override
    public BookNameRule getBookNameRule(Long id) {
        BookNameRule rules = bookNameRulesRepository.findById(id);
        return rules;
    }

    @Override
    public Map<String, String> getBookName(Long productId) {
        Product product = productRepository.findById(productId);
        //热词
        product.setRecommend(recommendService.getRecommend(productId));

        List<BookNameRule> bookNameRules = product.getBookNameRules();
        ProductWrapper productWrapper = ProductWrapper.builder(product, bookNameRules).build();
        Map<String, String> bookNameMap = new HashMap<>();
        for (BookNameRule bookNameRule : bookNameRules) {
            convertTitle(product, bookNameRule.getMetadata());
//            String name = titlesToName(titles, bookNameRule);
            String name = bookNameRule.getBookName();
            bookNameMap.put(bookNameRule.getShopId().toString(), name);
        }

        return bookNameMap;
    }

    private List<Metadata> convertTitle(Product product, List<Metadata> metadatas) {
        Class<?> clazz = null;
        Method method = null;
        try {
            clazz = Class.forName("xzj.model.Product");
            for (Metadata metadata : metadatas) {
                method = clazz.getMethod(metadata.getExpression());
                metadata.setValue((String) method.invoke(product, null));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return metadatas;
    }

    private String titlesToName(List<Metadata> metadata, BookNameRule bookNameRules) {
        Object[] values = metadata.stream().map(Metadata::getValue).toArray(String[]::new);

        MessageFormat format = new MessageFormat(bookNameRules.getRule());

        return format.format(values);
    }

}
