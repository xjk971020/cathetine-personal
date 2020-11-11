package com.cathetine.spring.schema;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

/**
 * @Description:自定义user命名空间的解析器
 * @Date: 2020/11/11
 */
public class UserBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class<?> getBeanClass(Element element) {
        return User.class;
    }

    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {
        String id = element.getAttribute("id");
        String name = element.getAttribute("name");
        String sex = element.getAttribute("sex");
        int age = Integer.parseInt(element.getAttribute("age"));

        builder.addPropertyValue("id", id);
        builder.addPropertyValue("name", name);
        builder.addPropertyValue("sex", sex);
        builder.addPropertyValue("age", age);

    }
}
