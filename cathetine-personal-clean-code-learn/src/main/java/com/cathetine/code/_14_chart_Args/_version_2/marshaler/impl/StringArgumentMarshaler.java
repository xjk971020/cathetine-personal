package com.cathetine.code._14_chart_Args._version_2.marshaler.impl;

import com.cathetine.code._14_chart_Args.ArgsException;
import com.cathetine.code._14_chart_Args._version_2.marshaler.ArgumentMarshaler;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static com.cathetine.code._14_chart_Args.ErrorCode.MISSING_STRING;

/**
 * @author xjk
 * @date 2020/7/18 -  10:40
 **/
public class StringArgumentMarshaler implements ArgumentMarshaler {
    private String stringValue = "";

    public void set(Iterator<String> currentArgument) throws ArgsException {
        try {
            stringValue = currentArgument.next();
        } catch (NoSuchElementException e) {
            throw new ArgsException(MISSING_STRING);
        }
    }

    public static String getValue(ArgumentMarshaler am) {
        if (am != null && am instanceof StringArgumentMarshaler) {
            return ((StringArgumentMarshaler) am).stringValue;
        } else {
            return "";
        }
    }
}