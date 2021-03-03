package com.company.методичка;

public class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(100);
                System.out.print("i");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
