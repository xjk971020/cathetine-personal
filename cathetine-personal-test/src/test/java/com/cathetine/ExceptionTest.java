package com.cathetine;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author:xjk
 * @Description:
 * @Date: 2020/7/14
 */
public class ExceptionTest {

    private static Logger log = LoggerFactory.getLogger(ExceptionTest.class);

    @Test
    public void testException() {
        try {
            System.out.println(11 / 0);
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println("--------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("--------------------------------------");
            System.out.println(e.toString());
            System.out.println("--------------------------------------");
            log.error("发生错误[{}]。", "11/0", e);
        }
    }
}
