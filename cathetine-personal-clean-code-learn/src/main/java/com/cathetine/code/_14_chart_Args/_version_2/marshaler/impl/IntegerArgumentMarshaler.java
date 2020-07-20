package com.cathetine.code._14_chart_Args._version_2.marshaler.impl;

import com.cathetine.code._14_chart_Args._version_2.ArgsException;
import com.cathetine.code._14_chart_Args._version_2.marshaler.ArgumentMarshaler;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static com.cathetine.code._14_chart_Args._version_2.ErrorCode.INVALID_INTEGER;
import static com.cathetine.code._14_chart_Args._version_2.ErrorCode.MISSING_INTEGER;

/**
 * @author xjk
 * @date 2020/7/18 -  10:40
 **/
public class IntegerArgumentMarshaler implements ArgumentMarshaler {
    private int intValue = 0;

    public void set(Iterator<String> currentArgument) throws ArgsException {
        String parameter = null;
        try {
            parameter = currentArgument.next();
            intValue = Integer.parseInt(parameter);
        } catch (NoSuchElementException e) {
            throw new ArgsException(MISSING_INTEGER);
        } catch (NumberFormatException e) {
            throw new ArgsException(INVALID_INTEGER, parameter);
        }
    }

    public static int getValue(ArgumentMarshaler am) {
        if (am != null && am instanceof IntegerArgumentMarshaler) {
            return ((IntegerArgumentMarshaler) am).intValue;
        } else {
            return 0;
        }
    }
}
