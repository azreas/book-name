package xzj;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import xzj.model.BookNameRule;
import xzj.model.Metadata;
import xzj.service.BookNameService;

import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BookNameApplicationTests {

    @Autowired
    BookNameService bookNameService;

    @Test
    public void getBookNameRule() throws Exception {
        BookNameRule bookNameRule = bookNameService.getBookNameRule(1L);
        List<Metadata> nameRules = bookNameRule.getNameRules();

        for (Metadata nameRule : nameRules) {
            System.out.println(nameRule);
        }
//        String s = bookNameRule.lengthRule2(0);
//        System.out.println(s);
    }

    @Test
    public void getBookName() throws Exception {
        Map<String, String> bookNameMap = bookNameService.getBookName(10001L);
        System.out.println(bookNameMap);
    }
}
