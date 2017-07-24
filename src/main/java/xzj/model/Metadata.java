package xzj.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @Author xzj
 * @Date 2017/7/18.
 */
@Entity
@Table(name = "title")
public class Metadata implements Comparable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @Column
    private int order;
    @Column
    private int priority;
    @Column(name = "_rule")
    private int ruleId;
    @Transient
    private String value;

    private String expression;

    public Metadata() {
    }

    public Metadata(String name, int order, int priority) {
        this.name = name;
        this.order = order;
        this.priority = priority;
    }

    public int getRuleId() {
        return ruleId;
    }

    public void setRuleId(int ruleId) {
        this.ruleId = ruleId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getExpression() {
        return expression;

    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return name + " : " + value;

    }

    @Override
    public int compareTo(Object o) {
        Metadata nameRule = (Metadata) o;
        return this.order - nameRule.order;
    }
}
