package xzj.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author xzj
 * @Date 2017/7/18.
 */
@Entity
@Table(name = "book_name_rules")
public class BookNameRule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String rule;
    private int length;
    @Column(name = "_shop")
    private Long shopId;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "_rule")
    private List<Metadata> metadata = new ArrayList<>();
    @Transient
    private List<Metadata> new_metadata = new ArrayList<>();

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
//    private Map<Integer, Metadata> orderMap;
//    private Map<Integer, Metadata> priorityMap;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void addTitle(Metadata nameRule) {
        metadata.add(nameRule);
    }

    public List<Metadata> getNameRules() {
        return metadata;
    }


    public List<Metadata> getNew_metadata() {
        return new_metadata;
    }

    public void setNew_metadata(List<Metadata> new_metadata) {
        this.new_metadata = new_metadata;
    }

    public List<Metadata> getMetadata() {
        return metadata;
    }

    public void setMetadata(List<Metadata> metadata) {
        this.metadata = metadata;
    }

    public String getBookName() {
        MessageFormat format = new MessageFormat(rule);
        Object[] values = checkLength(format, 0);
        return format.format(values);
    }


    public Object[] checkLength(MessageFormat format, int priority) {
        Object[] values = metadata.stream().map(Metadata::getValue).toArray(String[]::new);
        String bookName = format.format(values);
        System.out.println(bookName.length());
        if (bookName.length() > length && priority <= 10) {//避免死循环
            for (Metadata metadata : this.metadata) {
                if (metadata.getPriority() == priority) {
                    int titleLength = metadata.getValue().length();
                    if (titleLength > 4) {
                        metadata.setValue(metadata.getValue().substring(0, metadata.getValue().length() - 4));
                    } else {
                        metadata.setValue("");
                    }
                }
            }
            return checkLength(format, priority + 1);
        }
        return values;
    }

}
