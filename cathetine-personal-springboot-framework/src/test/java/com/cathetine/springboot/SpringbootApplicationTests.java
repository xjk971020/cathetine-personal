package com.cathetine.springboot;

import com.cathetine.model.User;
import com.cathetine.springboot.test_20200815.service.AbstractUserService;
import com.cathetine.springboot.test_20200815.service.Impl.PCUserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootApplicationTests {

    @Resource(name = "mobilUserServiceImpl")
    AbstractUserService userService;

    @Test
    public void contextLoads() {
        userService.addUser();
    }

}
