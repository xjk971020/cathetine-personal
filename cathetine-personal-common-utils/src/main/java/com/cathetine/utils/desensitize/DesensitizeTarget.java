package com.cathetine.utils.desensitize;

import java.lang.reflect.Field;

class DesensitizeTarget {
    Class<?> targetCls;
    DesensitizeTarget inheritDesensitizeTarget;
    private SensitizeField[] sensitizeFields;
    private Field[] iterableFields;

    public boolean hasSensitizeField(){
        return sensitizeFields != null;
    }

    public boolean hasIterableField(){
        return iterableFields != null;
    }

    public boolean hasInheritTarget(){
        return inheritDesensitizeTarget != null;
    }

    public Class<?> getTargetCls() {
        return targetCls;
    }


    public void setTargetCls(Class<?> targetCls) {
        this.targetCls = targetCls;
    }

    public DesensitizeTarget getInheritDesensitizeTarget() {
        return inheritDesensitizeTarget;
    }

    public void setInheritDesensitizeTarget(DesensitizeTarget inheritDesensitizeTarget) {
        this.inheritDesensitizeTarget = inheritDesensitizeTarget;
    }

    public SensitizeField[] getSensitizeFields() {
        return sensitizeFields;
    }

    public void setSensitizeFields(SensitizeField[] sensitizeFields) {
        this.sensitizeFields = sensitizeFields;
    }

    public Field[] getIterableFields() {
        return iterableFields;
    }

    public void setIterableFields(Field[] iterableFields) {
        this.iterableFields = iterableFields;
    }
}
