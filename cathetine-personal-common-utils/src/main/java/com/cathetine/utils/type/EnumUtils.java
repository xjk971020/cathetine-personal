package com.cathetine.utils.type;

import com.cathetine.utils.ReadWriteMap;
import com.cathetine.utils.type.IllegalEnumClassException;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.*;

/**
 * 枚举类工具
 */
public abstract class EnumUtils {

    // 用户缓存每个枚举类型的键值对列表
    private static ReadWriteMap<String, List<KeyPair>> keyPairListMap = new ReadWriteMap<>(new HashMap<>());

    // 用户缓存每个枚举类型的键值对对应的Map
    private static ReadWriteMap<String, Map<String,String>> keyPairMapMap = new ReadWriteMap<>(new HashMap<>());

    /**
     * 判断类型是否包含值
     * @param type 类型类
     * @param value 值
     * @return
     */
    public static boolean contains(Class<?> type, String value) {
        Map<String,String> keyPairMap = keyPairMapMap.get(type.getName());
        if(keyPairMap == null){
            loadEnum(type);
            keyPairMap = keyPairMapMap.get(type.getName());
        }
        return keyPairMap.containsKey(value);
    }

    /**
     * 判断类型是否包含值
     * @param type
     * @param value
     * @return
     */
    public static boolean contains(Class<?> type, Integer value) {
        return contains(type, Integer.valueOf(value).toString());
    }

    /**
     * 获取枚举类型值对应的描述信息
     * 值为null描述信息也返回null
     * @param type
     * @param value
     * @return
     */
    public static String descOf(Class<?> type, @Nullable String value) {
        if(value == null) return null;
        Map<String,String> keyPairMap = getMap(type);
        return keyPairMap.get(value);
    }

    /**
     * 获取枚举类型值对应的描述信息
     * 值为null描述信息也返回null
     * @param type
     * @param value
     * @return
     */
    public static String descOf(Class<?> type, @Nullable Integer value) {
        if(value == null) return null;
        return descOf(type, Integer.valueOf(value).toString());
    }

    /**
     * 根据描述信息获取枚举类型的值
     * 值为null枚举类型的值也返回null
     * @param type
     * @param desc
     * @return
     */
    public static String valueOf(Class<?> type, @Nullable String desc) {
        if (desc == null) {
            return null;
        }
        Map<String,String> keyPairMap = getMap(type);
        for (Map.Entry<String, String> entry : keyPairMap.entrySet()) {
            if (desc.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    public static String valueOf(Class<?> type, @Nullable Integer desc) {
        return valueOf(type, String.valueOf(desc));
    }

    /**
     * 获取枚举类对应键值对List
     * @param type
     * @return
     */
    public static List<KeyPair> getKeyPairs(Class<?> type) {
        List<KeyPair> keyPairList = keyPairListMap.get(type.getName());
        if(keyPairList == null){
            loadEnum(type);
            keyPairList = keyPairListMap.get(type.getName());
        }
        return keyPairList;
    }

    /**
     * <li>获取枚举类型对应的&lt;code,desc&gt;Map对象<li/>
     * @param type
     * @return
     */
    public static Map<String,String> getMap(Class<?> type){
        Map<String,String> keyPairMap = keyPairMapMap.get(type.getName());
        if(keyPairMap == null){
            loadEnum(type);
            keyPairMap = keyPairMapMap.get(type.getName());
        }
        return keyPairMap;
    }

    /**
     * 加载枚举类型
     * @param type
     */
    private static void loadEnum(Class<?> type) {

        Field[] fields = type.getDeclaredFields();
        ArrayList<KeyPair> keyPairList = new ArrayList<>();
        Map<String,String> keyPairMap = new LinkedHashMap<>();

        Include include = type.getAnnotation(Include.class);
        if(include != null){
            // 聚合其他枚举类型
            for(Class<?> includeCls: include.value()){

                // 获取被聚合的枚举类型的键值对 并添加到当前枚举对象
                List<KeyPair> keyPairs = getKeyPairs(includeCls);
                for (KeyPair keyPair :
                        keyPairs) {
                    // 检查是否存在重复定义
                    if (keyPairMap.containsKey(keyPair.getKey())) {
                        throw new IllegalEnumClassException("聚合枚举类"+type.getClass().getName()
                                +"key["+keyPair.getKey()+"]在其他被聚合的枚举类中已有定义");
                    } else {
                        keyPairList.add(keyPair);
                        keyPairMap.put(keyPair.getKey(), keyPair.getValue());
                    }

                }
            }
        }

        for (Field filed :
                fields) {

            // 内部类会有这个属性 直接跳过
            if(filed.getName().contains("$")){
                continue;
            }

            String fieldLabel = "Class " + type.getName() + " 中" + filed.getName() + "属性";

            // 检查修饰符
            checkModifier(fieldLabel, filed.getModifiers());

            // 检查字段类型
            checkFieldType(fieldLabel, filed.getType());

            KeyPair keyPair = getKeyPair(type, filed);

            // 检查是否存在重复定义
            if (keyPairMap.containsKey(keyPair.getKey())) {
                throw new IllegalEnumClassException(fieldLabel + "中值" + keyPair.getKey() + "重复定义");
            } else {
                keyPairList.add(keyPair);
                keyPairMap.put(keyPair.getKey(), keyPair.getValue());
            }
        }

        if (keyPairMap.isEmpty()) {
            throw new IllegalEnumClassException(type.getName() + "没有定义属性");
        }

        // 排序
        Collections.sort(keyPairList, Comparator.comparingInt(KeyPair::getOrder));

        // 放入缓存
        keyPairListMap.put(type.getName(), ImmutableList.copyOf(keyPairList));
        keyPairMapMap.put(type.getName(), ImmutableMap.copyOf(keyPairMap));

    }

    private static void checkModifier(String label, int modifiers) {
        if (!Modifier.isPublic(modifiers)) {
            throw new IllegalEnumClassException(label + "没有声明为public");
        }
        if (!Modifier.isStatic(modifiers)) {
            throw new IllegalEnumClassException(label + "没有声明为static");
        }
        if (!Modifier.isFinal(modifiers)) {
            throw new IllegalEnumClassException(label + "没有声明为final");
        }
    }

    private static void checkFieldType(String label, Type type) {
        if (!String.class.equals(type) && !Integer.class.equals(type) && !type.getTypeName().equals("int")) {
            throw new IllegalEnumClassException(label + "不是java.lang.String或java.lang.Integer类型");
        }
    }

    private static KeyPair getKeyPair(Class<?> clz, Field field) {
        try {
            field.setAccessible(true);
            Object value = field.get(clz);
            Enum enumAnnotation = field.getAnnotation(Enum.class);
            String desc = null;
            Integer order = null;

            if (enumAnnotation != null) {
                desc = enumAnnotation.desc();
                if ("".equals(desc)) {
                    desc = enumAnnotation.value();
                }
                order = enumAnnotation.order();
            }

            if (desc == null || "".equals(desc)) {
                desc = field.getName();
            }

            if (order == null) {
                order = 0;
            }

            KeyPair keyPair = new KeyPair();
            keyPair.setKey(value.toString());
            keyPair.setValue(desc);
            keyPair.setOrder(order);

            return keyPair;
        } catch (IllegalAccessException e) {
            throw new IllegalEnumClassException(e);
        }
    }


}
