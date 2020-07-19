package com.cathetine.code._14_chart_Args._version_2.marshaler.impl;

import com.cathetine.code._14_chart_Args.ArgsException;
import com.cathetine.code._14_chart_Args._version_2.marshaler.ArgumentMarshaler;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static com.cathetine.code._14_chart_Args.ErrorCode.INVALID_DOUBLE;
import static com.cathetine.code._14_chart_Args.ErrorCode.MISSING_DOUBLE;

/**
 * @author xjk
 * @date 2020/7/18 -  10:44
 **/
public class DoubleArgumentMarshaler implements ArgumentMarshaler {

    private Double doubleValue = 0.0;

    public void set(Iterator<String> currentArgument) throws ArgsException {
        String parameter = "";
        try {
            parameter = currentArgument.next();
            doubleValue = Double.parseDouble(parameter);
        } catch (NoSuchElementException e)  {
            throw new ArgsException(MISSING_DOUBLE);
        } catch(NumberFormatException e) {
            throw new ArgsException(INVALID_DOUBLE, parameter);
        }
    }

    public static double getValue(ArgumentMarshaler am) {
        if (am != null && am instanceof DoubleArgumentMarshaler) {
            return ((DoubleArgumentMarshaler)am).doubleValue;
        } else {
            return 0.0;
        }
    }
}
