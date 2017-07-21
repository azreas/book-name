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
//        ProductWrapper productWrapper = ProductWrapper.builder(product, bookNameRules).build();

//        Map<String, String> bookNameMap = new HashMap<>();
//        for (BookNameRule bookNameRule : bookNameRules) {
//            convertTitle(product, bookNameRule.getMetadata());
//            String name = titlesToName(titles, bookNameRule);
//            String name = bookNameRule.getBookName();
//            bookNameMap.put(bookNameRule.getShopId().toString(), name);
//        }

        for (BookNameRule bookNameRule : bookNameRules) {
            ProductWrapper.Builder builder = ProductWrapper.builder(product, bookNameRules);
            ProductWrapper productWrapper = buildMetadata(builder, bookNameRule.getMetadata(), product);
            System.out.println(productWrapper);
        }
//        return productWrapper.getShopAndNameMap();
        return null;
    }


    //    private List<Metadata> convertTitle(Product product, List<Metadata> metadatas) {
//        Class<?> clazz = null;
//        Method method = null;
//        try {
//            clazz = Class.forName("xzj.model.Product");
//            for (Metadata metadata : metadatas) {
//                method = clazz.getMethod(metadata.getExpression());
//                metadata.setValue((String) method.invoke(product, null));
//            }
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//        return metadatas;
//    }
    private ProductWrapper buildMetadata(ProductWrapper.Builder builder, List<Metadata> metadatas, Product product) {
        Class<?> clazz = null;
        Method method = null;

        try {
            clazz = this.getClass();
            for (Metadata metadata : metadatas) {
                method = clazz.getMethod("build" + metadata.getExpression(), Product.class, ProductWrapper.Builder.class);
//                builder
//                metadata.setValue((String) method.invoke(product, null));
                method.invoke(this,  product, builder);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return builder.build();
    }

    private String titlesToName(List<Metadata> metadata, BookNameRule bookNameRules) {
        Object[] values = metadata.stream().map(Metadata::getValue).toArray(String[]::new);

        MessageFormat format = new MessageFormat(bookNameRules.getRule());

        return format.format(values);
    }

    public void buildTitle(Product product, ProductWrapper.Builder builder) {
        builder.title(product.getBookName());
    }

    public void buildSubTitle(Product product, ProductWrapper.Builder builder) {
        builder.subtitle(product.getSubtitle());
    }

    public void buildMarketingClassification(Product product, ProductWrapper.Builder builder) {
        builder.marketingClassification(product.getMarketingClassification());
    }

    public void buildPreSale(Product product, ProductWrapper.Builder builder) {
        builder.preSale(product.getPreSale());
    }

    public void buildArrivalTime(Product product, ProductWrapper.Builder builder) {
        builder.arrivalTime(product.getArrivalTime());
    }

    public void buildHotWord(Product product, ProductWrapper.Builder builder) {
        builder.hotword(recommendService.getHotWord(product.getId()));
    }

    public void buildRecommend(Product product, ProductWrapper.Builder builder) {
        builder.recommend(recommendService.getRecommend(product.getId()));
    }

}
