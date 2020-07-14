package com.cathetine.utils;

import net.sf.cglib.beans.BeanCopier;

public abstract class BeanUtils {

    /**
     * 判断部分字段是否相等
     * @param <T>
     * @return
     */
    public static  <T> boolean fieldEquals(T entity1, T entity2, GetterFunction<T,?> getterFunction){
        Object value1 = getterFunction.getValue(entity1);
        Object value2 = getterFunction.getValue(entity2);
        return equalTo(value1, value2);
    }

    /**
     * 判断部分字段是否相等
     * @param <T>
     * @return
     */
    public static  <T> boolean fieldEquals(T entity1, T entity2,
                                           GetterFunction<T,?> getterFunction1,
                                           GetterFunction<T,?> getterFunction2){

        return fieldEquals(entity1, entity2, getterFunction1)
                && fieldEquals(entity1, entity2, getterFunction2);
    }

    /**
     * 判断部分字段是否相等
     * @param <T>
     * @return
     */
    public static  <T> boolean fieldEquals(T entity1, T entity2,
                                           GetterFunction<T,?> getterFunction1,
                                           GetterFunction<T,?> getterFunction2,
                                           GetterFunction<T,?> getterFunction3){

        return fieldEquals(entity1, entity2, getterFunction1)
                && fieldEquals(entity1, entity2, getterFunction2)
                && fieldEquals(entity1, entity2, getterFunction3);
    }

    /**
     * 判断部分字段是否相等
     * @param <T>
     * @return
     */
    public static  <T> boolean fieldEquals(T entity1, T entity2,
                                           GetterFunction<T,?>... getterFunctions){
        for(GetterFunction<T,?> getter: getterFunctions){
            if( !fieldEquals(entity1, entity2, getter) ){
                return false;
            }
        }

        return true;
    }

    /**
     * obj1.equals(obj2), obj1,obj2同时为null时返回true
     * @return
     */
    public static boolean equalTo(Object obj1, Object obj2){
        if(obj1 != null){
            return obj1.equals(obj2);
        }else {
            return obj2 == null;
        }
    }


    /**
     * 对象深拷贝
     */
    public static<T> T deepCopy(T src) {
        if(src == null){
            return src;
        }
        Class<T> beanCls = (Class<T>) src.getClass();
        BeanCopier beanCopier= BeanCopier.create(beanCls, beanCls, false);
        T target;
        try {
            target = beanCls.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        beanCopier.copy(src, target, null);
        return target;
    }
}
