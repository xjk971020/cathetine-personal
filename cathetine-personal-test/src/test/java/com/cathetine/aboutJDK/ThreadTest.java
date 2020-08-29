package com.cathetine.aboutJDK;

import com.cathetine.OrderPrinter;
import org.junit.Test;

/**
 * @author xjk
 * @date 2020/8/29 -  15:16
 **/
public class ThreadTest {

    public static void main(String[] args) {
        OrderPrinter orderPrinter = new OrderPrinter(5);
        new Thread(() -> orderPrinter.print(1)).start();
        new Thread(() -> orderPrinter.print(2)).start();
        new Thread(() -> orderPrinter.print(3)).start();
        new Thread(() -> orderPrinter.print(4)).start();
        new Thread(() -> orderPrinter.print(5)).start();
    }
}
