package com.cathetine;

import com.cathetine.model.Generics;
import com.cathetine.model.User;
import net.sf.cglib.core.CollectionUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:xjk
 * @Description:
 * @Date: 2020/7/14
 */
public class GenericsTest {
    @Test
    public void testGenerics() {
        List list = new ArrayList<>();
        list.add(1);
        if (list != null) {
            Object o = list.get(0);
        }

        List<?> list1 = new ArrayList<>();
//        list1.add(1);
        if (list1 != null) {
            Object o = list1.get(0);
        }

        List<String> list2 = new ArrayList<>();
        list2.add("test");
        if (list2 != null) {
            Object o = list2.get(0);
        }
    }
}
