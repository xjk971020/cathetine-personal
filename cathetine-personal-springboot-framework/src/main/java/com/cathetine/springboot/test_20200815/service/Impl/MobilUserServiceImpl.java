package com.cathetine.springboot.test_20200815.service.Impl;

import com.cathetine.model.User;
import com.cathetine.springboot.test_20200815.service.AbstractUserService;
import com.cathetine.springboot.test_20200815.stragety.ILogStragetyService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author xjk
 * @date 2020/8/15 -  16:29
 **/
@Service("mobilUserServiceImpl")
public class MobilUserServiceImpl extends AbstractUserService {
    public MobilUserServiceImpl(@Qualifier("consoleLogStragetyServicImpl") ILogStragetyService logStragetyService) {
        this.logStragetyService = logStragetyService;
    }

    @Override
    protected void log() {
        System.out.println("移动端添加用户，将日志记录到控制台");
        logStragetyService.log();
    }
}
