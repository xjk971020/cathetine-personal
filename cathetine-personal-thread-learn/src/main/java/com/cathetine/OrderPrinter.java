package com.cathetine;

import com.cathetine.utils.IntegerUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xjk
 * @date 2020/8/29 -  15:30
 * 启动N个线程, 这N个线程要不间断按顺序打印数字1-N. 将问题简化为3个线程无限循环打印1到3
 **/
public class OrderPrinter {
    private Lock lock = new ReentrantLock();

    private List<Condition> conditions;

    private volatile Integer state = 1;
    private Integer count;

    public OrderPrinter(int count) {
        this.count = count;
        this.conditions = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            conditions.add(this.lock.newCondition());
        }
    }

    public void print(Integer state) {
        try {
            lock.lock();
            while (true) {
                while (IntegerUtil.isNotEquals(state, this.state)) {
                   conditions.get(state - 1).await();
                }
                System.out.println(state);
                this.state = state % count + 1;
                conditions.get(this.state - 1).signal();
                conditions.get(state - 1).await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
