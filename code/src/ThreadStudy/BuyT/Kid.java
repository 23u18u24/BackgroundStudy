package BuyT;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Kid implements Runnable {
    private int ticket = 100;
    Lock l = new ReentrantLock();

    Object obj = new Object();
    @Override
    public void run() {

//        //第一种使用
//        synchronized (obj) {
//            while (ticket > 0) {
//                try {
//                    Thread.sleep(10L);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(Thread.currentThread().getName() + "您买的是第" + ticket + "张票");
//                ticket--;
//            }
//        //第二种
//        buyT();
//        }

        //第三种Lock锁
        while (true) {
            l.lock();
            if (ticket > 0) {
                try {
                    Thread.sleep(10L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(Thread.currentThread().getName() + "您买的是第" + ticket + "张票");
                    ticket--;
                    l.unlock();
                }
            }
        }
    }

    private synchronized void buyT() {
        try {
            Thread.sleep(10L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "您买的是第" + ticket + "张票");
        ticket--;
    }
}