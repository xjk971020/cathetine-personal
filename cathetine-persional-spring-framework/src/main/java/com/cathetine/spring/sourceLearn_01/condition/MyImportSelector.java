package com.cathetine.spring.sourceLearn_01.condition;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author xjk
 * @date 2020/8/30 -  20:57
 **/
public class MyImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"com.cathetine.spring.sourceLearn_01.bean.yellow","com.cathetine.spring.sourceLearn_01.bean.blue"};
    }
}
