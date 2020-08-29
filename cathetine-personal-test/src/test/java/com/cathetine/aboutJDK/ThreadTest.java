package com.cathetine.aboutJDK;

import com.cathetine.OrderPrinter;
import org.junit.Test;

/**
 * @author xjk
 * @date 2020/8/29 -  15:16
 **/
public class ThreadTest {
    @Test
    public void test() throws InterruptedException {
        OrderPrinter orderPrinter = new OrderPrinter(5);
        new Thread(() -> orderPrinter.print(1)).start();
        new Thread(() -> orderPrinter.print(2)).start();
        new Thread(() -> orderPrinter.print(3)).start();
        new Thread(() -> orderPrinter.print(4)).start();
        new Thread(() -> orderPrinter.print(5)).start();

        //需要在主线程中加休眠时间，不然看不到效果，在junit中，主线程执行完成程序便会退出。
        Thread.sleep(1000000000);
    }

}
