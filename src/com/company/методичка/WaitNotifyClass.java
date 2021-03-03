package com.company.методичка;

public class WaitNotifyClass {
    private final Object mon = new Object();
    private volatile boolean currentLetter = true;

    public static void main(String[] args) {
        WaitNotifyClass w = new WaitNotifyClass();
        Thread t1 = new Thread(() -> {
            w.printA();
        });
        Thread t2 = new Thread(() -> {
            w.printB();
        });
        t1.start();
        t2.start();
    }



    public void printA() {
        synchronized (mon) {
            try {
                for (int i = 0; i < 3; i++) {
                    while (!currentLetter ) {
                        mon.wait();
                    }
                    System.out.println("printA");
                    currentLetter = false;
                    mon.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }






    public void printB() {
        synchronized (mon) {
            try {
                for (int i = 0; i < 3; i++) {
                    while (currentLetter ) {
                        mon.wait();
                    }
                    System.out.println("printB");
                    currentLetter = true;
                    mon.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


