package org.bl.concurrent;

import java.util.concurrent.Semaphore;

public class TestSemaphore {

    private class Worker extends Thread {
        private int num;
        private Semaphore semaphore;

        public Worker(int num, Semaphore semaphore) {
            this.num = num;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            System.out.println("Doing " + num);
            try {
                semaphore.acquire();
                Thread.sleep(num * 1000 + 1000);
                semaphore.release();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            System.out.println("Done " + num);
        }
    }

    private void begin() {
        Semaphore semaphore = new Semaphore(5);
        for (int i = 0; i < 10; i++) {
            new Worker(i, semaphore).start();
        }
    }

    public static void main(String[] args) {
        new TestSemaphore().begin();
    }
}
