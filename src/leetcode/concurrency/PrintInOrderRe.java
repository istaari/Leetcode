package leetcode.concurrency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PrintInOrderRe {

    public PrintInOrderRe() {
    }

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition first = lock.newCondition();
    private final Condition second = lock.newCondition();
    private final Condition third = lock.newCondition();

    private String state = "First";

    public void first(Runnable printFirst) throws InterruptedException {
        lock.lock();

        printFirst.run();
        lock.unlock();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        lock.lock();
        printSecond.run();
        lock.unlock();
    }

    public void third(Runnable printThird) throws InterruptedException {
        lock.lock();
        printThird.run();
        lock.unlock();
    }



    public static void main(String[] args) throws InterruptedException {
        PrintInOrderRe print = new PrintInOrderRe();

        Thread t1 = new Thread(() -> {
            try {
                print.first(() -> System.out.println("First"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                print.second(() -> System.out.println("Second"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread t3 = new Thread(() -> {
            try {
                print.third(() -> System.out.println("Third"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        t1.start();
        t3.start();
        t2.start();

    }
}
