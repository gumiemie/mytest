package com.guyang.sources.thread;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description 用一句话描述这个类
 *
 * * 建立三个线程，A线程打印10次A，B线程打印10次B,C线程打印10次C，要求线程同时运行，交替打印10次ABC。
 * 这个问题用Object的wait()，notify()就可以很方便的解决。
 *
 * 主要的思想就是，为了控制执行的顺序，必须要先持有prev锁，也就前一个线程要释放自身对象锁，再去申请自身对象锁，
 * 两者兼备时打印，之后首先调用self.notify()释放自身对象锁，唤醒下一个等待线程，再调用prev.wait()释放prev对象锁，
 * 终止当前线程，等待循环结束后再次被唤醒。
 *
 * 程序运行的主要过程就是A线程最先运行，持有C,A对象锁，后释放A,C锁，唤醒B。线程B等待A锁，再申请B锁，后打印B，
 * 再释放B，A锁，唤醒C，线程C等待B锁，再申请C锁，后打印C，再释放C,B锁，唤醒A。
 *
 * wait导致当前的线程等待，直到其他线程调用此对象的 notify() 要领或 notifyAll() 要领。当前的线程必须拥有此对象监视器。该线程揭晓对此监视器的一切权并等待，直到其他线程议决调用 notify 要领，或 notifyAll 要领告诉在此对象的监视器上等待的线程醒来。然后该线程将等到重新获得 对监视器的一切权后才能继续执行.

　  notify唤醒在此对象监视器上等待的单个线程。假如一切线程都在此对象上等待，则会挑选唤醒其中一个线程。直到当前的线程放弃此对象上的锁定，才能继续执行被唤醒的线程。此要领只应由作为此对象监视器的一切者的线程来调用.

　　"当前的线程必须拥有此对象监视器"与"此要领只应由作为此对象监视器的一切者的线程来调用"表明 wait要领与notify要领必须在同步块内执行,即synchronized(obj之内).

　　调用对像wait要领后,当前线程释放对像锁,进入等待形状 .直到其他线程(也只好是其他线程)议决 notify 要领，或 notifyAll.该线程重新获得 对像锁.

　　继续执行,记得线程必须重新获得 对像锁才能继续执行.由于 synchronized代码块内没有锁是寸步无法走的.

notify()和notifyAll()都是Object对象用于通知处在等待该对象的线程的方法。两者的最大区别在于：

notifyAll使所有原来在该对象上等待被notify的线程统统退出wait的状态，变成等待该对象上的锁，一旦该对象被解锁，他们就会去竞争。
notify则文明得多他只是选择一个wait状态线程进行通知，并使它获得该对象上的锁，但不惊动其他同样在等待被该对象notify的线程们，当第一个线程运行完毕以后释放对象上的锁此时如果该对象没有再次使用notify语句，则即便该对象已经空闲，其他wait状态等待的线程由于没有得到该对象的通知，继续处在wait状态，直到这个对象发出一个notify或notifyAll，它们等待的是被notify或notifyAll，而不是锁。
 *
 * @date 2018/5/28 11:46$
 */
public class ThreadWaitTest implements Runnable {

    private String name;
    private Object prev;
    private Object self;

    private ThreadWaitTest(String name, Object prev, Object self) {
        this.name = name;
        this.prev = prev;
        this.self = self;
    }

    public void run() {
        int count = 10;
        while (count > 0) {
            //同步块，加锁
            synchronized (prev) {
                synchronized (self) {
                    System.out.print(name);
                    count--;
                    //唤醒等待self对象的线程，即等待给self对象加锁的线程，假如有多个线程随机选取一个。
                    self.notify();
                }
                //同步块线束后，self对象释放锁
                try {
                    //当前线程释放prev对象的锁，进入等待状态
                    prev.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }//同步块结束后，prev释放锁
        }
    }

    public static void main(String[] args) throws Exception {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();

        //创建三个线程对象
        ThreadWaitTest hwta = new ThreadWaitTest("A", c, a);
        ThreadWaitTest hwtb = new ThreadWaitTest("B", a, b);
        ThreadWaitTest hwtc = new ThreadWaitTest("C", b, c);

        /**
         *
         * 线程a:进入可运行状态，拿到c，a的锁，打印“A”.然后唤醒线程b,接着释放a锁，然后使线程a进入等待状态，之后会释放c锁。
         * 线程b:进入可运行状态，等待拿到a,b的锁，拿到后打印“B” , 然后唤醒等待b锁的线程（即线程c）,然后释放b锁，然后使当前线程进入等待状态，之后会释放a对象的锁
         * 线程c:进入可运行状态，拿到b,c的锁，拿到后打印“C”,然后唤醒等对象c的锁的线程（即线程a），接着释放c锁，然后使当前线程进入等待状态，之后会释放b对象的锁
         */
        new Thread(hwta).start();
        Thread.sleep(100);
        new Thread(hwtb).start();
        Thread.sleep(100);
        new Thread(hwtc).start();
        Thread.sleep(1000);
    }
}
