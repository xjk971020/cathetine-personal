package com.cathetine.code._14_chart_Args._version_2.marshaler.impl;

import com.cathetine.code._14_chart_Args.ArgsException;
import com.cathetine.code._14_chart_Args._version_2.marshaler.ArgumentMarshaler;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static com.cathetine.code._14_chart_Args.ErrorCode.MISSING_STRING;
import static com.cathetine.code._14_chart_Args.ErrorCode.STRING_ARRAY_TOO_LONG;

/**
 * @author xjk
 * @date 2020/7/18 -  10:45
 **/
public class StringArrayArgumentMarshaler implements ArgumentMarshaler {

    private String[] stringArrayValue;

    private static final Integer STRING_ARRAY_LENGTH = 100;

    private Integer currentIndex = 0;

    public void set(Iterator<String> currentArgument) throws ArgsException {
        try {
            while (currentArgument.hasNext()) {
                stringArrayValue[currentIndex++] = currentArgument.next();
                judgeIndex();
            }
        } catch (NoSuchElementException e) {
            throw new ArgsException(MISSING_STRING);
        }
    }

    private void judgeIndex() throws ArgsException {
        if (currentIndex > STRING_ARRAY_LENGTH - 1) {
            throw new ArgsException(STRING_ARRAY_TOO_LONG);
        }
    }

    public static String[] getValue(ArgumentMarshaler am) {
        if (am != null && am instanceof StringArrayArgumentMarshaler) {
            return ((StringArrayArgumentMarshaler)am).stringArrayValue;
        } else {
            return null;
        }
    }
}
