import com.cathetine.model.User;
import com.cathetine.spring.sourceLearn_01.config.MainConfig;
import com.cathetine.spring.sourceLearn_01.config.MainConfig2;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author xjk
 * @date 2020/8/30 -  20:35
 **/
public class TestMainConfig {
    AnnotationConfigApplicationContext annotationConfigApplicationContext;

    @Before
    public void before() {
        annotationConfigApplicationContext
                = new AnnotationConfigApplicationContext(MainConfig.class);
        System.out.println("IOC容器初始化....");
    }

    @Test
    public void test() {
        printBeanDefinitionName(annotationConfigApplicationContext);
//        User user_chinese = (User) annotationConfigApplicationContext.getBean("user");
//        User user_chinese2 = (User) annotationConfigApplicationContext.getBean("user_chinese");
//        System.out.println(user_chinese == user_chinese2);

    }

    private void printBeanDefinitionName(ApplicationContext applicationContext) {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }
}
