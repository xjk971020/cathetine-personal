package com.cathetine.springboot.test_20200815.stragety.impl;

import com.cathetine.springboot.test_20200815.stragety.ILogStragetyService;
import org.springframework.stereotype.Service;

/**
 * @author xjk
 * @date 2020/8/15 -  16:01
 * 将日志记录在文件中
 **/
@Service("fileLogStragetyServiveImpl")
public class FileLogStragetyServiveImpl implements ILogStragetyService {
    public void log() {
        System.out.println("log in file.");
    }
}
