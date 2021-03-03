package com.company;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    static final int SIZE = 10000000;
    static final int HALF = SIZE / 2;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        System.out.println(" создаю FixedThreadPool с 10 потоками ");
        // write your code here
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("Начало Метода с 1 потоком");
                firstM();

            }
        });
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("Начало 2го Метода и передаю ссылку на FixedThreadPool");
                secondM(executorService);

            }
        });

    }

     static void secondM(ExecutorService executorService) {
        long a = System.currentTimeMillis();
        System.out.println("начало многопоточного метода, время ==>   " + (System.currentTimeMillis() - a));
        Object lock1 = new Object();
        Object lock2 = new Object();
        float[] arr1 = new float[SIZE];
        float[] arr2 = new float[SIZE];
        System.out.println("создал 2 манитора для практики многопоточный метод, время ==>   " + (System.currentTimeMillis() - a));

        executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("первый loop подсчет до середины многопоточный метод, время ==> " + (System.currentTimeMillis() - a));
                synchronized (lock1) {
                    System.out.println("первый loop закрыл монитор lock1 многопоточный метод, время ==> " + (System.currentTimeMillis() - a));
                    for (int i = 0; i < HALF; i++) {
                        arr1[i] = 1;
                        arr1[i] = (float) (arr1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                    }
                    System.out.println("\tпервый loop открыл монитор lock1 многопоточный метод, время ==> " + (System.currentTimeMillis() - a));
                }
            }
        });
        executorService.submit(new Runnable() {
            @Override
            public void run() {

                System.out.println("второй loop подсчет от середины многопоточный метод, время ==> " + (System.currentTimeMillis() - a));
                synchronized (lock2) {
                    System.out.println("второй loop закрыл монитор lock2 многопоточный метод, время ==> " + (System.currentTimeMillis() - a));
                    for (int i = HALF; i < arr2.length; i++) {
                        arr2[i] = 1.0f;
                        arr2[i] = (float) (arr2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                    }
                }
                System.out.println("\tвторой loop открыл монитор lock2 многопоточный метод, время ==> " + (System.currentTimeMillis() - a));
            }
        });
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("ждем мониторов lock1 и lock2 для склейки масивов многопоточный метод, время ==> " + (System.currentTimeMillis() - a));
                synchronized (lock1) {
                    System.out.println("монитор lock1 отрыт многопоточный метод, время ==> " + (System.currentTimeMillis() - a));
                    synchronized (lock2) {
                        System.out.println("монитор lock2 отрыт многопоточный метод, время ==> " + (System.currentTimeMillis() - a));

                        System.out.println("начало склейки многопоточный метод, время ==> " + (System.currentTimeMillis() - a));
                        System.arraycopy(
                                arr1, //    массив-назначение,
                                HALF, //    сколько ячеек копируем)
                                arr2, //    откуда начинаем брать данные из массива-источника, откуда начинаем записывать данные в массив-назначение
                                HALF, //    откуда начинаем записывать данные в массив-назначение
                                HALF); //   сколько ячеек копируем)
                    }
                }
                System.out.println("склейка завершена! многопоточный метод, время ==> " + (System.currentTimeMillis() - a));
                System.out.println("2й метод завершен  время ==> " + (System.currentTimeMillis() - a));
            }

        });


        executorService.shutdown();
        System.out.println();
        System.out.println("\t\t\t\t\texecutorService.shutdown()?!\n если executorService зарыт то почему потоки продолжают работу?\n многопоточный метод, время ==>  " + (System.currentTimeMillis() - a));
        System.out.println();


    }


    static void firstM() {
        long a = System.currentTimeMillis();
        System.out.print("конец метода с 1 потоком ==>   ");
        System.out.println(System.currentTimeMillis() - a);
        float[] arr = new float[SIZE];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }

        System.out.print("конец метода с 1 потоком ==> ");
        System.out.println(System.currentTimeMillis() - a);
    }


}




