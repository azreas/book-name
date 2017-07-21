package xzj;

import xzj.model.BookNameRule;
import xzj.model.Metadata;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author xzj
 * @Date 2017/7/18.
 */
public class BookNameTest {
    public static void main(String[] args) {
        Metadata metadata1 = new Metadata("书产品名", 0, 3);
        Metadata metadata2 = new Metadata("副书名", 1, 2);
        Metadata metadata3 = new Metadata("通过营销分类得到的热词 ", 3, 0);
        Metadata metadata4 = new Metadata("通过营销分类得到的推荐", 2, 1);
        BookNameRule bookName = new BookNameRule();
//        bookName.addTitle(metadata4);
//        bookName.addTitle(metadata2);
//        bookName.addTitle(metadata3);
//        bookName.addTitle(metadata1);
//        bookName.setRule("{0}:{1}+{2}+{3}");
//        System.out.println(bookName.getBookName());
//        bookName.setLength(20);
//        Map<Integer, Metadata> map =
//                bookName.getNameRules().stream().collect(Collectors.toMap(Metadata::getPriority, title -> title));
//        Map<Integer, String> map = new HashMap<>();
//        map.put(0, "13546545");
//        map.put(1, "heretrtegdfg");
//        String substring = map.get(1).substring(0,3);
//        map.put(1, substring);
//        System.out.println(map);
//        List<Metadata> nameRules = bookName.getNameRules();
//        myprint(nameRules);
//        Collections.sort(nameRules);
//        myprint(nameRules);

//        MessageFormat.format();
//        System.out.println("%s ",bookName.getBookName());
    }

    // 自定义方法：分行打印输出list中的元素
    public static void myprint(List<Metadata> list) {
        Iterator it = list.iterator(); // 得到迭代器，用于遍历list中的所有元素
        while (it.hasNext()) {// 如果迭代器中有元素，则返回true
            System.out.println("\t" + it.next());// 显示该元素
        }
    }

    // 自定义比较器：按书的价格排序
    static class OrderComparator implements Comparator {
        public int compare(Object object1, Object object2) {// 实现接口中的方法
            Metadata metadata1 = (Metadata) object1; // 强制转换
            Metadata metadata2 = (Metadata) object2;
            return metadata1.compareTo(metadata2);
        }
    }
}
