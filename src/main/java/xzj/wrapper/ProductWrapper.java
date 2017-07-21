package xzj.wrapper;

import xzj.model.BookNameRule;
import xzj.model.Metadata;
import xzj.model.Product;

import java.text.MessageFormat;
import java.util.List;

/**
 * @Author xzj
 * @Date 2017/7/21.
 */
public class ProductWrapper {
    private final Product product;
    private final List<BookNameRule> bookNameRules;
    private final 

    private ProductWrapper(Builder builder) {
        this.product = builder.product;
        this.bookNameRules = builder.bookNameRules;
    }

    public static Builder builder(Product product, List<BookNameRule> bookNameRules) {
        return new Builder(product, bookNameRules);
    }

    public static class Builder {
        private Product product;
        private List<BookNameRule> bookNameRules;

        Builder(Product product, List<BookNameRule> bookNameRules) {
            if (product == null) throw new NullPointerException("product == null");
            if (bookNameRules == null) throw new NullPointerException("bookNameRules == null");
            this.product = product;
            this.bookNameRules = bookNameRules;
        }

        public ProductWrapper build() {
            return new ProductWrapper(this);
        }
    }


}
