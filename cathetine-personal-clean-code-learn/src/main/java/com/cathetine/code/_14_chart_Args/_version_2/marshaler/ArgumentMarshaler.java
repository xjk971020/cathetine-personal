package com.cathetine.code._14_chart_Args._version_2.marshaler;

import com.cathetine.code._14_chart_Args.ArgsException;

import java.util.Iterator;

/**
 * @author xjk
 * @date 2020/7/18 -  10:39
 **/
public interface ArgumentMarshaler {
    void set(Iterator<String> currentArgument) throws ArgsException;
}
