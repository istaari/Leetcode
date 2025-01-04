package leetcode.concurrency;


public class PrintInOrderMon {
    private String state = "First";

    public PrintInOrderMon() {
    }

    public synchronized void first(Runnable printFirst) throws InterruptedException {
        while (!state.equals("First")) {
            wait(); // Releases the locks
        }
        printFirst.run();
        state = "Second";
        notifyAll();
    }

    public synchronized void second(Runnable printSecond) throws InterruptedException {
        while (!state.equals("Second")) {
            wait();
        }
        printSecond.run();
        state = "Third";
        notifyAll();
    }

    public synchronized void third(Runnable printThird) throws InterruptedException {
        while (!state.equals("Third")) {
            wait();
        }
        printThird.run();
    }


    public static void main(String[] args) throws InterruptedException {
        PrintInOrderMon print = new PrintInOrderMon();

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
        t2.start();
        t3.start();
    }
}
