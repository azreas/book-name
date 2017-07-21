package xzj.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * @Author xzj
 * @Date 2017/7/20.
 */
@Entity
@Table(name = "product")
public class Product {
    @Id
    private Long id;
    @Column(name = "m1")
    private String bookName;
    @Column(name = "m2")
    private String subtitle;
    @Column(name = "m3")
    private String marketingClassification;
    @Column(name = "m5")
    private String preSale;
    @Column(name = "m6")
    private String arrivalTime;
    private String m7;
    private String m10;
    private String m11;
    @Transient
    private String recommend;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "product_rule",
            joinColumns = @JoinColumn(name = "_product"),
            inverseJoinColumns = @JoinColumn(name = "_rule")
    )
    private List<BookNameRule> bookNameRules;

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    public List<BookNameRule> getBookNameRules() {
        return bookNameRules;
    }

    public void setBookNameRules(List<BookNameRule> bookNameRules) {
        this.bookNameRules = bookNameRules;
    }

    public String getMarketingClassification() {
        return marketingClassification;
    }

    public void setMarketingClassification(String marketingClassification) {
        this.marketingClassification = marketingClassification;
    }

    public String getPreSale() {
        return preSale;
    }

    public void setPreSale(String preSale) {
        this.preSale = preSale;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }


    public String getM7() {
        return m7;
    }

    public void setM7(String m7) {
        this.m7 = m7;
    }

    public String getM10() {
        return m10;
    }

    public void setM10(String m10) {
        this.m10 = m10;
    }

    public String getM11() {
        return m11;
    }

    public void setM11(String m11) {
        this.m11 = m11;
    }


}
