package xzj.wrapper;

import xzj.model.BookNameRule;
import xzj.model.Product;

import java.util.List;

/**
 * @Author xzj
 * @Date 2017/7/21.
 */
public class ProductWrapper {
    private Product product;
    private List<BookNameRule> bookNameRules;
    private String title;
    private String subtitle;
    private String marketingClassification;
    private String preSale;
    private String arrivalTime;
    private String hotWord;
    private String recommend;
    private String version;
    private String author;
    private String brand;
    private String fascicle;

    private ProductWrapper(Builder builder) {
//        this.product = builder.product;
//        this.bookNameRules = builder.bookNameRules;
        this.title = builder.title;
        this.subtitle = builder.subtitle;
        this.marketingClassification = builder.marketingClassification;
        this.preSale = builder.preSale;
        this.arrivalTime = builder.arrivalTime;
        this.hotWord = builder.hotWord;
        this.recommend = builder.recommend;
        this.version = builder.version;
        this.author = builder.author;
        this.brand = builder.brand;
        this.fascicle = builder.fascicle;
    }

    public static Builder builder(Product product, List<BookNameRule> bookNameRules) {
        return new Builder(product, bookNameRules);
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getFascicle() {
        return fascicle;
    }

    public String getAuthor() {
        return author;
    }

    public String getRecommend() {
        return recommend;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getMarketingClassification() {
        return marketingClassification;
    }

    public String getPreSale() {
        return preSale;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public String getHotWord() {
        return hotWord;
    }

    public String getVersion() {
        return version;
    }

    public String getBrand() {
        return brand;
    }


    @Override
    public String toString() {
        return "ProductWrapper{" +
                "title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", marketingClassification='" + marketingClassification + '\'' +
                ", preSale='" + preSale + '\'' +
                ", arrivalTime='" + arrivalTime + '\'' +
                ", hotWord='" + hotWord + '\'' +
                ", recommend='" + recommend + '\'' +
                ", version='" + version + '\'' +
                ", author='" + author + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }

    public static class Builder {
        private Product product;
        private List<BookNameRule> bookNameRules;
        private String title;
        private String subtitle;
        private String marketingClassification;
        private String preSale;
        private String arrivalTime;
        private String hotWord;
        private String recommend;
        private String version;
        private String author;
        private String brand;
        private String fascicle;


        Builder() {

        }

        Builder(Product product, List<BookNameRule> bookNameRules) {
            if (product == null) throw new NullPointerException("product == null");
            if (bookNameRules == null) throw new NullPointerException("bookNameRules == null");
            this.product = product;
//            for (BookNameRule bookNameRule : bookNameRules) {
//                List<Metadata> metadatas = bookNameRule.getMetadata();
//                convertTitle(product, metadatas);
//            }
            this.bookNameRules = bookNameRules;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder brand(String brand) {
            this.brand = brand;
            return this;
        }

        public Builder subtitle(String subtitle) {
            this.subtitle = subtitle;
            return this;
        }

        public Builder marketingClassification(String marketingClassification) {
            this.marketingClassification = marketingClassification;
            return this;
        }

        public Builder preSale(String preSale) {
            this.preSale = preSale;
            return this;
        }

        public Builder arrivalTime(String arrivalTime) {
            this.arrivalTime = arrivalTime;
            return this;
        }

        public Builder hotWord(String hotWord) {
            this.hotWord = hotWord;
            return this;
        }

        public Builder recommend(String recommend) {
            this.recommend = recommend;
            return this;
        }

        public Builder version(String version) {
            this.version = version;
            return this;
        }

        public Builder author(String author) {
            this.author = author;
            return this;
        }

        public Builder fascicle(String fascicle) {
            this.fascicle = fascicle;
            return this;
        }

        public ProductWrapper build() {
            return new ProductWrapper(this);
        }

    }
}
