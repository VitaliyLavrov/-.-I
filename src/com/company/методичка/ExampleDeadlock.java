package com.company.методичка;



public class ExampleDeadlock {
    private static Object lock1 = new Object();
    private static Object lock2 = new Object();


    private static class ThreadOne extends Thread{
        @Override
        public void run() {
          synchronized (lock1){
              System.out.println("Thread1 захватил Lock1");
              try {
                  Thread.sleep(1000);
              }catch (InterruptedException e){
                  e.printStackTrace();
              }
              System.out.println("Thread1 ожидает Lock2");
              synchronized (lock2){
                  System.out.println("Thread1 захватил Lock1 и Lock2");
              }
          }
        }
    }
private static class Threadtwo extends Thread{
        @Override
        public void run() {
          synchronized (lock2){
              System.out.println("Thread2 захватил Lock2");
              try {
                  Thread.sleep(1000);
              }catch (InterruptedException e){
                  e.printStackTrace();
              }
              System.out.println("Thread2 ожидает Lock1");
              synchronized (lock1){
                  System.out.println("Thread1 захватил Lock1 и Lock2");
              }
          }
        }
    }



    public static void main(String[] args) {
        ThreadOne threadOne = new ThreadOne();
        Threadtwo threadtwo = new Threadtwo();
        threadOne.start();
        threadtwo.start();
    }

}
