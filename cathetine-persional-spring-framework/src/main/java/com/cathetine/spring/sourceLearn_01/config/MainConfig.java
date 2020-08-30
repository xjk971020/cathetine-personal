package com.cathetine.spring.sourceLearn_01.config;

import com.cathetine.model.User;
import org.springframework.context.annotation.*;

/**
 * @author xjk
 * @date 2020/8/30 -  16:08
 **/
@Configuration
@ComponentScans(
        value = {
                @ComponentScan(value="com.cathetine.spring.sourceLearn_01",includeFilters = {
/*						@Filter(type=FilterType.ANNOTATION,classes={Controller.class}),
						@Filter(type=FilterType.ASSIGNABLE_TYPE,classes={BookService.class}),*/
                        @ComponentScan.Filter(type= FilterType.CUSTOM,classes={MyTypeFilter.class})
                },useDefaultFilters = false)
        }
)
//@ComponentScan  value:指定要扫描的包
//excludeFilters = Filter[] ：指定扫描的时候按照什么规则排除那些组件
//includeFilters = Filter[] ：指定扫描的时候只需要包含哪些组件
//FilterType.ANNOTATION：按照注解
//FilterType.ASSIGNABLE_TYPE：按照给定的类型；
//FilterType.ASPECTJ：使用ASPECTJ表达式
//FilterType.REGEX：使用正则指定
//FilterType.CUSTOM：使用自定义规则
public class MainConfig {

    //这里name和value属性只能设置一个，因为用了@AliasFor注解设置互为别名
    //要么只设置一个，要么必须值相同
    @Bean(name = "user_chinese")
    public User userFromChina() {
        User user = new User();
        user.setUserName("cat");
        user.setNation("China");
        return user;
    }

    @Bean(name = {"user_usa", "usa_canada"})
    public User userFromUSA() {
        User user = new User();
        user.setUserName("cat");
        user.setNation("USA");
        return user;
    }

}
