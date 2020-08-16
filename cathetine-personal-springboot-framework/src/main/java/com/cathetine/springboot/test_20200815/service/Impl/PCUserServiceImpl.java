package com.cathetine.springboot.test_20200815.service.Impl;

import com.cathetine.model.User;
import com.cathetine.springboot.test_20200815.service.AbstractUserService;
import com.cathetine.springboot.test_20200815.stragety.ILogStragetyService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author xjk
 * @date 2020/8/15 -  16:10
 * 通过PC端注册用户,日志记录到文件中
 **/
@Service("PCUserServiceImpl")
public class PCUserServiceImpl extends AbstractUserService {

    public PCUserServiceImpl(@Qualifier("fileLogStragetyServiveImpl")ILogStragetyService logStragetyService) {
        this.logStragetyService = logStragetyService;
    }

    @Override
    protected void log() {
        System.out.println("PC端添加用户，将日志记录到文件中");
        logStragetyService.log();
    }
}
