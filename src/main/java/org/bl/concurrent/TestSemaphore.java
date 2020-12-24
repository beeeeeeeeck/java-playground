package org.bl.concurrent;

import java.util.concurrent.Semaphore;

public class TestSemaphore {

    private static class Worker extends Thread {
        private final int num;
        private final Semaphore semaphore;

        public Worker(int num, Semaphore semaphore) {
            this.num = num;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            System.out.println("Doing " + num);
            try {
                semaphore.acquire();
                Thread.sleep(5000);
                semaphore.release();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            System.out.println("Done " + num);
        }
    }

    private void begin() {
        Semaphore semaphore = new Semaphore(5);
        for (int i = 0; i < 20; i++) {
            new Worker(i, semaphore).start();
        }
    }

    public static void main(String[] args) {
        new TestSemaphore().begin();
    }
}
