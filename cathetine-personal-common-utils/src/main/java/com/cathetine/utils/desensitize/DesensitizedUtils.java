package com.cathetine.utils.desensitize;

import com.cathetine.utils.BeanUtils;
import com.cathetine.utils.ReadWriteMap;
import com.cathetine.utils.desensitize.Desensitized;

import java.lang.reflect.Field;
import java.util.*;

public abstract class DesensitizedUtils {

    public static String desensitize(SensitiveType type, String value){

        switch (type){
            case ID_CARD:
                return desensitize(value, 6, 4);
            case MOBILE_PHONE:
                return desensitize(value, 3, 4);
            case CHINESE_NAME:
                if(value == null || value.length() == 0) {
                    return value;
                }
                if(value.length() == 1){
                    return "*";
                }
                if(value.length() == 2){
                    return desensitize(value, 1,0);
                }
                return desensitize(value, 1, 1);
            case BANK_CARD:
                return desensitize(value,6, 4);
            default:
                return desensitize(value, 0, 0);
        }

    }


    public static String desensitize(String value, int prefix, int suffix){
        if(value == null|| value.length() <= prefix+suffix) {
            return value;
        }

        StringBuilder stringBuilder = new StringBuilder(value.length());

        stringBuilder.append(value.substring(0, prefix));
        int desensitizedCharCnt = value.length() - prefix - suffix;

        for(int i=0; i<desensitizedCharCnt; i++){
            stringBuilder.append('*');
        }

        stringBuilder.append(value.substring(value.length()-suffix, value.length()));

        return stringBuilder.toString();
    }

    private final static ReadWriteMap<Class<?>, DesensitizeTarget> desensitizeTargetMap = new ReadWriteMap<>(new HashMap<>());

    private static DesensitizeTarget loadDesensitizeTarget(Class<?> cls){

        return desensitizeTargetMap.syncOperate(map -> loadDesensitizeTarget(cls, map));
    }

    private static DesensitizeTarget loadDesensitizeTarget(Class<?> cls, Map<Class<?>, DesensitizeTarget> cache){

        DesensitizeTarget directTarget = null;

        DesensitizeTarget currTarget = null;

        Class<?> currCls = cls;
        List<Class<?>> waitForRegClsList = new ArrayList<>();

        while (!Object.class.equals(currCls)){
            if(cache.containsKey(currCls)){
                // cls已经注册过的情况
                DesensitizeTarget cachedTarget = cache.get(cls);
                if(cachedTarget != null){
                    if(directTarget == null){
                        directTarget = cachedTarget;
                    }else {
                        currTarget.setInheritDesensitizeTarget(cachedTarget);
                    }
                }

                if(!waitForRegClsList.isEmpty()){
                    regCls(waitForRegClsList, cache, cachedTarget);
                    waitForRegClsList.clear();
                }

                // 如果当前类已经注册过 那么父类必定已经注册了 直接跳出循环
                break;
            }else {
                // cls没有注册过的情况
                waitForRegClsList.add(currCls);

                DesensitizeTarget target = getDesensitizeTarget(currCls);
                if(target.hasSensitizeField() || target.hasSensitizeField()){

                    // 找到了 需要进行脱敏处理的类
                    if(directTarget == null){
                        directTarget = target;
                    }else {
                        currTarget.setInheritDesensitizeTarget(target);
                    }
                    currTarget = target;

                    // 将等待注册class注册到缓存
                    regCls(waitForRegClsList, cache, target);
                    waitForRegClsList.clear();
                }
                // 自下往上分析 继续分析父类
                currCls = currCls.getSuperclass();
            }
        }

        regCls(waitForRegClsList, cache, null);

        return directTarget;
    }

    private static void regCls(List<Class<?>> classes, Map<Class<?>, DesensitizeTarget> map, DesensitizeTarget desensitizeTarget){
        for (Class<?> cls:
             classes) {
            map.put(cls, desensitizeTarget);
        }
    }

    private static DesensitizeTarget getDesensitizeTarget(Class<?> cls){
        Field[] fields = cls.getDeclaredFields();

        DesensitizeTarget desensitizeTarget = new DesensitizeTarget();

        List<SensitizeField> sensitiveFieldList = new LinkedList<>();
        List<Field> iterableFieldList = new LinkedList<>();

        for (Field field :
                fields) {
            Class<?> fieldType = field.getType();

            // 判断是否为集合类型，这一类需要运行时动态获取类型，这里严谨一点通过判断继承关系来判断是否集合类型
            // 但是我们在定义POJO对象是否一般不会使用集合具体实现类作为声明
            if(Set.class.equals(fieldType) || List.class.equals(fieldType)){
                field.setAccessible(true);
                iterableFieldList.add(field);
            }else {
                Desensitized desensitized = field.getAnnotation(Desensitized.class);
                if(desensitized == null){
                    continue;
                }

                if(!String.class.equals(fieldType)){
                    throw new DesensitizeException(cls.getName()+"中"+field.getName()+"属性不是java.lang.String类型，无法进行脱敏。");
                }

                SensitizeField sensitizeField = new SensitizeField();
                field.setAccessible(true);
                sensitizeField.setField(field);
                sensitizeField.setDesensitized(desensitized);
                sensitiveFieldList.add(sensitizeField);
            }
        }
        desensitizeTarget.setTargetCls(cls);
        if(sensitiveFieldList.size() > 0){
            SensitizeField[] sensitizeFields = new SensitizeField[sensitiveFieldList.size()];
            sensitiveFieldList.toArray(sensitizeFields);
            desensitizeTarget.setSensitizeFields(sensitizeFields);
        }
        if(iterableFieldList.size() > 0){
            Field[] fields2 = new Field[iterableFieldList.size()];
            iterableFieldList.toArray(fields2);
            desensitizeTarget.setIterableFields(fields2);
        }
        return desensitizeTarget;
    }

    public static<T> T desensitizeObject(T bean){
        return desensitizeObject(bean, true);
    }

    /**
     * 对POJO对象进行脱敏处理
     * @param bean POJO对象
     * @param duplicate 脱敏时是否创建副本 以免对源数据造成影响 注意：即使这个选项为true，如果对象没有需要脱敏的字段
     *                 也会返回原来的对象
     * @param <T>
     * @return 脱敏后的对象
     */
    public static<T> T desensitizeObject(T bean, boolean duplicate){
        if(bean == null) {
            return bean;
        }

        DesensitizeTarget target = null;
        if( !desensitizeTargetMap.containsKey(bean.getClass()) ){
            target = loadDesensitizeTarget(bean.getClass());
        }else {
            target = desensitizeTargetMap.get(bean.getClass());
        }

        if(target == null){
            return bean;
        }

        T copyBean;
        if(duplicate){
            copyBean = BeanUtils.deepCopy(bean);
        }else {
            copyBean = bean;
        }

        do{
            if(target.hasSensitizeField()){
                for(SensitizeField sensitizeField: target.getSensitizeFields()){
                    sensitizeField.desensitize(copyBean);
                }
            }

            if(target.hasIterableField()){
                for(Field field : target.getIterableFields()){
                    try {
                        Iterable iterable  = (Iterable) field.get(copyBean);
                        if(iterable!=null){
                            for (Object subBean :
                                    iterable) {
                                desensitizeObject(subBean, false);
                            }
                        }

                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

        }while ( (target = target.getInheritDesensitizeTarget()) != null);

        return copyBean;
    }
}
