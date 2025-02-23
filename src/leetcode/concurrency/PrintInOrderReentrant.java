package leetcode.concurrency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PrintInOrderReentrant {

    public PrintInOrderReentrant() {
    }

    private final ReentrantLock lock = new ReentrantLock();

    private final Condition first = lock.newCondition();
    private final Condition second = lock.newCondition();
    private final Condition third = lock.newCondition();

    private String state = "First";

    public void first(Runnable printFirst) throws InterruptedException {
        lock.lock();

        while (!state.equals("First")) {
            first.await(); // Releases the locks
        }

        printFirst.run();
        state = "Second";

        second.signal();
        lock.unlock();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        lock.lock();

        while (!state.equals("Second")) {
            second.await(); // Releases the locks
        }

        printSecond.run();
        state = "Third";

        third.signal();
        lock.unlock();
    }

    public void third(Runnable printThird) throws InterruptedException {
        lock.lock();

        while (!state.equals("Third")) {
            third.await(); // Releases the locks
        }

        printThird.run();
        lock.unlock();
    }

    public static void main(String[] args) throws InterruptedException {
        PrintInOrderReentrant print = new PrintInOrderReentrant();

        // Threads to print "First", "Second", "Third"
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

        // Start threads
        t1.start();
        t2.start();
        t3.start();
    }
}
