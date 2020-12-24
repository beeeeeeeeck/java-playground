package org.bl.concurrent;

import java.util.concurrent.Exchanger;

/**
 * @author beckl
 */
public class TestExchanger {
    public static void main(String[] args) throws InterruptedException {
        Exchanger<String> exchanger = new Exchanger<>();

        new Thread(() -> {
            try {
                System.out.println("Thread A waiting for data from B at this point: " + exchanger.exchange("Data sent to B"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        System.out.println("A is blocked at this point and wait for the data from B");
        Thread.sleep(1000);

        new Thread(() -> {
            try {
                System.out.println("Thread B waiting for data from A at this point: " + exchanger.exchange("Data sent to A"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
