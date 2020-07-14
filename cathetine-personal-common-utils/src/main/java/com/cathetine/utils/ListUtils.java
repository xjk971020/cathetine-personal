package com.cathetine.utils;

import com.cathetine.utils.type.SetterFunction;

import java.util.*;

public abstract class ListUtils {

    /**
     * 将List转换为Map
     * @param list
     * @param getter
     * @param <T>
     * @return
     */
    public static<K, T> Map<K,T> listToMap(Iterable<T> list, GetterFunction<? super T, K> getter){
        Map<K,T> map = new LinkedHashMap<>();
        for(T item: list){
            K key = getter.getValue(item);
            map.put(key, item);
        }
        return map;
    }

    /**
     * 将List转换为Map
     * @param list
     * @param keyGetter 取Key函数
     * @param valueGetter 取Value函数
     * @param <K> key类型
     * @param <V> value类型
     * @return
     */
    public static<K, V, T> Map<K,V> fieldToMap(Iterable<T> list
            , GetterFunction<? super T, K> keyGetter
            , GetterFunction<? super T, V> valueGetter){
        Map<K,V> map = new HashMap<>();
        for(T item: list){
            K key = keyGetter.getValue(item);
            V value = valueGetter.getValue(item);
            map.put(key, value);
        }
        return map;
    }

    /**
     * 获取List中对象的单个属性的List
     * @param list
     * @param getter
     * @param <T>
     * @return
     */
    public static<K,T> List<K> fieldToList(Iterable<T> list, GetterFunction< ? super T, K> getter){
        List<K> vList = new ArrayList<>();

        for(T item: list){
            K field = getter.getValue(item);
            vList.add(field);
        }
        return vList;
    }

    public static<K,T> List<K> fieldToDistList(Iterable<T> list, GetterFunction< ? super T, K> getter){
        Set<K> fieldSet = new TreeSet<>();
        for(T item: list){
            K field = getter.getValue(item);
            fieldSet.add(field);
        }
        return  new ArrayList<>(fieldSet);
    }

    /**
     * 获取List中对象的单个属性的List,并且去掉重复的值
     * @param list
     * @param getter
     * @param <T>
     * @return
     */
    public static<T> List<Object> getDistValues(Iterable<T> list, GetterFunction<? super T, ?> getter){
        Set<Object> fieldSet = new TreeSet<>();
        for(T item: list){
            Object field = getter.getValue(item);
            fieldSet.add(field);
        }
        return  new ArrayList<>(fieldSet);
    }

    /**
     * 根据Key设置Value值
     * @param list
     * @param keyGetter 取key
     * @param valueSetter 取value
     * @param map
     * @param <T>
     * @param <K>
     * @param <V>
     */
    public static<T,K,V> void join(Iterable<T> list
            , GetterFunction<T, K> keyGetter
            , SetterFunction<T, V> valueSetter
            , Map<K,V> map){

        for(T bean: list){
            K key = keyGetter.getValue(bean);
            if(key != null){
                V value =  map.get(key);
                valueSetter.setValue(bean, value);
            }

        }

    }

    /**
     * 给list分组
     * @param list
     * @param keyGetter 取key函数
     * @param <K>
     * @param <T>
     * @return
     */
    public static<K, T> Map<K, List<T>> group(Iterable<T> list, GetterFunction<T, K> keyGetter){
        Map<K, List<T>> map = new LinkedHashMap<>();
        for(T bean: list){
            K key = keyGetter.getValue(bean);
            List<T> subList = map.get(key);
            if(subList == null){
                subList = new ArrayList<>();
                map.put(key, subList);
            }
            subList.add(bean);
        }
        return map;
    }

    /**
     * 初始化字段值
     */
    public static<V, T> void initFiled(Iterable<T> list, SetterFunction<T, V> setter , Supplier<V> supplier){
        for(T bean: list){
            setter.setValue(bean, supplier.get());
        }
    }

    /**
     * 初始化字段值
     */
    public static<V, T> void initFiledWithVal(Iterable<T> list, SetterFunction<T, V> setter , V value){
        for(T bean: list){
            setter.setValue(bean, value);
        }
    }

    /**
     * 提取满足条件对象
     * @param list
     * @param matchFunction
     * @param <T>
     * @return
     */
    public static<T> List<T> filter(Iterable<T> list, MatchFunction<T> matchFunction){
        List<T> resultList = new LinkedList<>();
        for(T bean: list){
            if(matchFunction.isMatch(bean)){
                resultList.add(bean);
            }
        }
        return resultList;
    }

    /**
     * 判断是否存在指定值
     * @param list 列表
     * @param getter 字段的getter方法
     * @param value 比较的值
     * @param <T>
     * @param <V>
     * @return
     */
    public static<T,V> boolean containsValue(Iterable<T> list, GetterFunction<T, V> getter , V value){
        for(T bean: list){
            V beanVal = getter.getValue(bean);
            if(BeanUtils.equalTo(beanVal, value)){
                return true;
            }
        }
        return false;
    }

    /**
     * 求和
     * @param list
     * @param getter
     * @param <T>
     * @return
     */
    public static <T> long sumLong(Iterable<T> list, GetterFunction<T, Long> getter){
        long sum = 0;
        for(T bean: list){
            Long value = getter.getValue(bean);
            if(value != null){
                sum += value;
            }
        }
        return sum;
    }

    /**
     * 求和
     * @param list
     * @param getter
     * @param <T>
     * @return
     */
    public static <T> int sumInt(Iterable<T> list, GetterFunction<T, Integer> getter){
        int sum = 0;
        for(T bean: list){
            Integer value = getter.getValue(bean);
            if(value != null){
                sum += value;
            }
        }
        return sum;
    }

}
