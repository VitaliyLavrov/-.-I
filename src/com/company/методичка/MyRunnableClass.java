package com.company.методичка;

public class MyRunnableClass implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(100);
                System.out.print("0");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
