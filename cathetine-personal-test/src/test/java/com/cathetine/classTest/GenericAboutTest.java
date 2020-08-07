package com.cathetine.classTest;

import com.cathetine.model.AbstractGenerics;
import com.cathetine.model.ConcreteBiGenerics;
import com.cathetine.model.ConcreteGenerics;
import com.cathetine.model.User;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * @Author:xjk
 * @Description:
 * @Date: 2020/8/6
 */
public class GenericAboutTest {

    @Test
    public void test() {
        User user = new User();
        Type userType = user.getClass();
        if (userType instanceof Class) {
            System.out.println("User super class is instance of Class");
        }

        AbstractGenerics generics = new ConcreteGenerics();
        Type genericsClass = generics.getClass().getGenericSuperclass();
        if (genericsClass instanceof Class) {
            System.out.println(((Class) genericsClass).getName());
            System.out.println("Generics<?> super class is instance of Class");
        }

        Type[] genericActualTypeArguments = ((ParameterizedType) genericsClass).getActualTypeArguments();
        System.out.println(genericActualTypeArguments.toString());

        ArrayList list = new ArrayList<>();
        Type genericSuperclass = list.getClass().getGenericSuperclass();
        if (genericSuperclass instanceof Class) {
            System.out.println("List<String> super class is instance of Class");
        }
        Type genericActualTypeArgument = ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
        if (genericActualTypeArgument instanceof Object) {
            System.out.println(genericActualTypeArgument);
        }

        ConcreteBiGenerics<String, User> concreteBiGenerics = new ConcreteBiGenerics<>();
        Type concreteBiGenericsSuperclass = concreteBiGenerics.getClass().getGenericSuperclass();
        if (concreteBiGenericsSuperclass instanceof Class) {
            System.out.println("ConcreteBiGenerics<String, User> super class is instance of Class");
        }
        Type[] actualTypeArguments = ((ParameterizedType) concreteBiGenericsSuperclass).getActualTypeArguments();
        for (Type actualTypeArgument : actualTypeArguments) {
            System.out.print(actualTypeArgument.getTypeName() + " ");
        }
    }
}


