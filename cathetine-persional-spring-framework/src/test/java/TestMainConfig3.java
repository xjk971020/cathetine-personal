import com.cathetine.spring.sourceLearn_01.bean.Cat;
import com.cathetine.spring.sourceLearn_01.bean.Dog;
import com.cathetine.spring.sourceLearn_01.bean.Yellow;
import com.cathetine.spring.sourceLearn_01.config.MainConfig2;
import com.cathetine.spring.sourceLearn_01.config.MainConfig3;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author xjk
 * @date 2020/8/30 -  22:40
 **/
public class TestMainConfig3 {
    AnnotationConfigApplicationContext annotationConfigApplicationContext;

    @Before
    public void before() {
        annotationConfigApplicationContext
                = new AnnotationConfigApplicationContext(MainConfig3.class);
    }

    @Test
    public void test() {
//        Yellow yellow = (Yellow) annotationConfigApplicationContext.getBean("yellow");
//        Cat cat = (Cat) annotationConfigApplicationContext.getBean("cat");
        Dog dog = (Dog) annotationConfigApplicationContext.getBean("dog");

        annotationConfigApplicationContext.close();
    }
}
