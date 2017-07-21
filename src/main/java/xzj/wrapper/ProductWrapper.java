package xzj.wrapper;

import xzj.model.BookNameRule;
import xzj.model.Metadata;
import xzj.model.Product;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author xzj
 * @Date 2017/7/21.
 */
public class ProductWrapper {
    private final Product product;
    private final List<BookNameRule> bookNameRules;
//    private final Map<String, String> shopAndNameMap;

    private ProductWrapper(Builder builder) {
        this.product = builder.product;
        this.bookNameRules = builder.bookNameRules;
    }

    public static Builder builder(Product product, List<BookNameRule> bookNameRules) {
        return new Builder(product, bookNameRules);
    }

    public Map<String, String> getShopAndNameMap() {
        Map<String, String> shopAndNameMap = new HashMap<>();
        for (BookNameRule bookNameRule : bookNameRules) {
            shopAndNameMap.put(bookNameRule.getShopId().toString(), getBookName(bookNameRule));
        }
        return shopAndNameMap;
    }

    private String getBookName(BookNameRule bookNameRule) {
        MessageFormat format = new MessageFormat(bookNameRule.getRule());
        Object[] values = bookNameRule.getMetadata().stream().map(Metadata::getValue).toArray(String[]::new);

//        Object[] values = checkLength(format, 0);
        return format.format(values);
    }

    public static class Builder {
        private Product product;
        private List<BookNameRule> bookNameRules;

        Builder(Product product, List<BookNameRule> bookNameRules) {
            if (product == null) throw new NullPointerException("product == null");
            if (bookNameRules == null) throw new NullPointerException("bookNameRules == null");
            this.product = product;
            for (BookNameRule bookNameRule : bookNameRules) {
                List<Metadata> metadatas = bookNameRule.getMetadata();
                convertTitle(product, metadatas);
            }
            this.bookNameRules = bookNameRules;
        }

        public void setHotWord() {

        }

        public ProductWrapper build() {
            return new ProductWrapper(this);
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
    }


}
