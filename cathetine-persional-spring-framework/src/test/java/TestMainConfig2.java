import com.cathetine.model.User;
import com.cathetine.spring.sourceLearn_01.config.MainConfig;
import com.cathetine.spring.sourceLearn_01.config.MainConfig2;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

/**
 * @author xjk
 * @date 2020/8/30 -  20:35
 **/
public class TestMainConfig2 {
    AnnotationConfigApplicationContext annotationConfigApplicationContext;

    @Before
    public void before() {
        annotationConfigApplicationContext
                = new AnnotationConfigApplicationContext(MainConfig2.class);
    }

    @Test
    public void test() {
//        User user_chinese = (User) annotationConfigApplicationContext.getBean("user");
//        User user_chinese2 = (User) annotationConfigApplicationContext.getBean("user");
//        System.out.println(user_chinese == user_chinese2);

//        String[] names = annotationConfigApplicationContext.getBeanDefinitionNames();
//        for (String name : names) {
//            System.out.println(name);
//        }
//
//        Map<String, User> users = annotationConfigApplicationContext.getBeansOfType(User.class);
//        System.out.println(users);

        Object colorFactoryBean = annotationConfigApplicationContext.getBean("&colorFactoryBean");
        System.out.println(colorFactoryBean.getClass());
    }
}
