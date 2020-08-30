package com.cathetine.spring.sourceLearn_01.condition;

import com.cathetine.spring.sourceLearn_01.bean.RainBow;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

	/**
	 * AnnotationMetadata����ǰ���ע����Ϣ
	 * BeanDefinitionRegistry:BeanDefinitionע���ࣻ
	 * 		��������Ҫ��ӵ������е�bean������
	 * 		BeanDefinitionRegistry.registerBeanDefinition�ֹ�ע�����
	 */
	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		
		boolean definition = registry.containsBeanDefinition("com.cathetine.spring.sourceLearn_01.bean.Yellow");
		boolean definition2 = registry.containsBeanDefinition("com.cathetine.spring.sourceLearn_01.bean.Blue");
		if(definition && definition2){
			//ָ��Bean������Ϣ����Bean�����ͣ�Bean��������
			RootBeanDefinition beanDefinition = new RootBeanDefinition(RainBow.class);
			System.out.println("ע��Rainbow.class....");
			//ע��һ��Bean��ָ��bean��
			registry.registerBeanDefinition("rainBow", beanDefinition);
		}
	}

}
