package com.company.методичка;

public class Example_SB_1 {

    public static void main(String[] args) {
        Example_SB_1 e1 = new Example_SB_1();
        new Thread(() -> e1.method1()).start();
        new Thread(() -> e1.method2()).start();
    }

    public  void method1() {
        System.out.println("M1start");
        for (int i = 0; i < 10; i++) {
            System.out.println("M1");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("M1finish");
    }

    public  void method2() {
        System.out.println("M2start");
        for (int i = 0; i < 10; i++) {
            System.out.println("M2");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("M2finish");
    }

}
