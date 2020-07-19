package com.cathetine.code._14_chart_Args._version_2.marshaler.impl;

import com.cathetine.code._14_chart_Args.ArgsException;
import com.cathetine.code._14_chart_Args._version_2.marshaler.ArgumentMarshaler;

import java.util.Iterator;

/**
 * @author xjk
 * @date 2020/7/18 -  10:39
 **/
public class BooleanArgumentMarshaler implements ArgumentMarshaler {
    private boolean booleanValue = false;

    public void set(Iterator<String> currentArgument) throws ArgsException {
        booleanValue = true;
    }

    public static boolean getValue(ArgumentMarshaler am) {
        if (am != null && am instanceof BooleanArgumentMarshaler) {
            return ((BooleanArgumentMarshaler) am).booleanValue;
        } else {
            return false;
        }
    }
}
