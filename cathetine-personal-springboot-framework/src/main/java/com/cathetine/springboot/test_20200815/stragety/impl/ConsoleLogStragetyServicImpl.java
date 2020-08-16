package com.cathetine.springboot.test_20200815.stragety.impl;

import com.cathetine.springboot.test_20200815.stragety.ILogStragetyService;
import org.springframework.stereotype.Service;

/**
 * @author xjk
 * @date 2020/8/15 -  16:00
 * 将日志记录在控制台
 **/
@Service("consoleLogStragetyServicImpl")
public class ConsoleLogStragetyServicImpl implements ILogStragetyService {
    public void log() {
        System.out.println("log in console.");
    }
}
