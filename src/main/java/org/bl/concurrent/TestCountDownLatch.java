package org.bl.concurrent;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class TestCountDownLatch {

    public static final int COUNT_NUM = 4;
    private CountDownLatch countDownLatch = new CountDownLatch(COUNT_NUM);

    private class Runner implements Runnable {
        private int result;

        public Runner(int result) {
            this.result = result;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(result * 1000);
                countDownLatch.countDown();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void begin() {
        System.out.println("Begin ...");
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < COUNT_NUM; i++) {
            int result = random.nextInt(3) + 1;
            new Thread(new Runner(result)).start();
        }

        try {
            countDownLatch.await(); // wait for the count down to zero
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("Done.");
    }

    public static void main(String[] args) {
        TestCountDownLatch test = new TestCountDownLatch();
        test.begin();
    }
}
