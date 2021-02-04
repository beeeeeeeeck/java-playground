package org.bl.concurrent;

import java.util.concurrent.CyclicBarrier;

public class TestCyclicBarrier {
    public static final int NUM = 5;
    private final CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

    private class Student implements Runnable {
        private final int num;

        public Student(int num) {
            this.num = num;
        }

        @Override
        public void run() {
            System.out.println("Set up " + num);
            try {
                Thread.sleep(num * 1000L);
                cyclicBarrier.await();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("Ready " + num);
        }
    }

    private void begin() {
        for (int i = 0; i < NUM; i++) {
            new Thread(new Student(i + 1)).start();
        }
    }

    public static void main(String[] args) {
        new TestCyclicBarrier().begin();
    }
}
