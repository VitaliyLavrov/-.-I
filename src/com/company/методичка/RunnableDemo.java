package com.company.методичка;

public class RunnableDemo {


    static class RunnableClass implements Runnable {
        boolean suspended = false;

        public void run() {
            System.out.println("Запуск потока");
            try {
                for (int i = 100; i > 0; i--) {
                    System.out.println(i);
                    Thread.sleep(300);
                    synchronized (this) {
                        if (suspended) {
                            System.out.println("ga");

                            wait();

                        }
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("Поток прерван");
            }
            System.out.println("Завершение потока");
        }
        public void mySuspend(){
            suspended = true;
        }
        public synchronized void myResume(){
            suspended=false;
            notify();
        }

    }


    public static void main(String[] args) {
        RunnableClass rc = new RunnableClass();
      Thread tr =  new Thread(rc);

        tr.start();

        try {
            for (int i = 0; i < 3; i++) {
                System.out.println(tr.getState());

                Thread.sleep(800);
                rc.mySuspend();

                System.out.println(tr.getState());
                Thread.sleep(3000);
                rc.myResume();

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }




}

