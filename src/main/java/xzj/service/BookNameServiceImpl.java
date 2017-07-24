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
import java.util.Collections;
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
    public Map<Long, String> getBookName(Long productId) {
        Product product = productRepository.findById(productId);
        List<BookNameRule> bookNameRules = bookNameRulesRepository.findByProductId(productId);
        Map<Long, String> shopAndNameMap = new HashMap<>();
        for (BookNameRule bookNameRule : bookNameRules) {
            ProductWrapper productWrapper = buildMetadata(ProductWrapper.builder(), bookNameRule.getMetadatas(), product);
            List<Metadata> metadatas = convertMetadata(productWrapper, bookNameRule.getMetadatas());
            MessageFormat format = new MessageFormat(bookNameRule.getRule());
            Collections.sort(metadatas);
            String[] strings = checkLength(0, metadatas, format, bookNameRule.getLength());
            String bookName = convertBookName(format, strings);
//            System.out.println(productWrapper);
//            System.out.println(metadatas);
            shopAndNameMap.put(bookNameRule.getShopId(), bookName);
        }
        return shopAndNameMap;
    }

    private String convertBookName(MessageFormat format, String[] values) {
        return format.format(values);
    }

    private String[] checkLength(int priority, List<Metadata> metadatas, MessageFormat format, int length) {
        String[] values = metadatas.stream().map(Metadata::getValue).toArray(String[]::new);
        String bookName = format.format(values);
        if (bookName.length() > length && priority <= 10) {//避免死循环， priority>10的不做修改
            for (Metadata metadata : metadatas) {
                if (metadata.getPriority() == priority) {
                    //TODO 根据具体修改规则进行修改
                    metadata.setValue("");
                }
            }
            return checkLength(priority + 1, metadatas, format, length);
        }
        return values;
    }

    private List<Metadata> convertMetadata(ProductWrapper productWrapper, List<Metadata> metadatas) {
        Class<?> clazz = null;
        Method method = null;
        try {
            clazz = Class.forName("xzj.wrapper.ProductWrapper");
            for (Metadata metadata : metadatas) {
                method = clazz.getDeclaredMethod("get" + metadata.getExpression());
                metadata.setValue((String) method.invoke(productWrapper, null));
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

    private ProductWrapper buildMetadata(ProductWrapper.Builder builder, List<Metadata> metadatas, Product product) {
        Class<?> clazz = null;
        Method method = null;

        try {
            clazz = this.getClass();
            for (Metadata metadata : metadatas) {
                method = clazz.getDeclaredMethod("build" + metadata.getExpression(), Product.class, ProductWrapper.Builder.class);
                method.invoke(this, product, builder);
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

    public void buildSubtitle(Product product, ProductWrapper.Builder builder) {
        builder.subtitle(product.getSubtitle());
    }

    public void buildMarketingClassification(Product product, ProductWrapper.Builder builder) {
        builder.marketingClassification(product.getMarketingClassification());
    }

    public void buildPreSale(Product product, ProductWrapper.Builder builder) {
        Boolean preSale = product.getPreSale();
        if (preSale) {
            builder.preSale("预售");
        } else {
            builder.preSale("");
        }

    }

    public void buildArrivalTime(Product product, ProductWrapper.Builder builder) {
        Boolean preSale = product.getPreSale();
        if (preSale) {
            builder.arrivalTime(product.getArrivalTime());
        } else {
            builder.arrivalTime("");
        }
    }

    public void buildHotWord(Product product, ProductWrapper.Builder builder) {
        builder.hotWord(recommendService.getHotWord(product.getId()));
    }

    public void buildRecommend(Product product, ProductWrapper.Builder builder) {
        builder.recommend(product.getRecommend());
    }

    public void buildVersion(Product product, ProductWrapper.Builder builder) {
        builder.version(product.getVersion());
    }

    public void buildAuthor(Product product, ProductWrapper.Builder builder) {
        builder.author(product.getAuthor());
    }

    public void buildBrand(Product product, ProductWrapper.Builder builder) {
        builder.brand(product.getBrand());
    }

    public void buildFascicle(Product product, ProductWrapper.Builder builder) {
        builder.fascicle(product.getFascicle());
    }
}
