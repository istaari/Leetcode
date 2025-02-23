package leetcode.concurrency;

import java.util.concurrent.Semaphore;

public class PrintInOrderSemaphore {

    Semaphore first = new Semaphore(1);
    Semaphore second = new Semaphore(0);
    Semaphore third = new Semaphore(0);

    public PrintInOrderSemaphore() {
    }

    public void first(Runnable printFirst) throws InterruptedException {
        first.acquire();
        printFirst.run();
        second.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        second.acquire();
        printSecond.run();
        third.release();
    }

    public void third(Runnable printThird) throws InterruptedException {
        third.acquire();
        printThird.run();
    }

}
