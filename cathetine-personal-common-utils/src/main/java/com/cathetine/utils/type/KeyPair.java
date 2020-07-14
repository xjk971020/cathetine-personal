package com.cathetine.utils.type;

import java.io.Serializable;

public class KeyPair implements Serializable {

    private String key;
    private String value;
    private Integer order;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    protected Integer getOrder() {
        return order;
    }

    protected void setOrder(Integer order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "KeyPair{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
