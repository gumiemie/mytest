package com.guyang.sources.thread;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description 线程 例子
 * @date 2018/5/23 11:31$
 */
public class ThreadDemo implements Runnable {
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(i);
            try {
                Thread.sleep((int) Math.random() * 10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ThreadDemo threadDemo1 = new ThreadDemo();
        ThreadDemo threadDemo2 = new ThreadDemo();
        Thread thread1 = new Thread(threadDemo1);
        Thread thread2 = new Thread(threadDemo2);
        thread1.start();
        thread1.join();
        thread2.start();
        thread2.join();

        System.out.println("sssssssssssssss");
    }


}
