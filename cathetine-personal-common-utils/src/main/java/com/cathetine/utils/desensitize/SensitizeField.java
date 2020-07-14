package com.cathetine.utils.desensitize;

import com.cathetine.utils.desensitize.Desensitized;
import com.cathetine.utils.desensitize.DesensitizedUtils;

import java.lang.reflect.Field;

class SensitizeField {
    private Field field;
    private Desensitized desensitized;

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public Desensitized getDesensitized() {
        return desensitized;
    }

    public void setDesensitized(Desensitized desensitized) {
        this.desensitized = desensitized;
    }

    public void desensitize(Object bean){
        try {
            Object value = field.get(bean);
            if(value != null){
                String desensitizeValue = DesensitizedUtils.desensitize(desensitized.value(), (String) value);
                field.set(bean, desensitizeValue);
            }

        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }
}
