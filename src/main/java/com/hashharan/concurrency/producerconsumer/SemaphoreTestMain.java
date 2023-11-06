package com.hashharan.concurrency.producerconsumer;

public class SemaphoreTestMain {
    public static void main(String[] args) {

        IProducerConsumer<Integer> pc = new SemaphoreProducerConsumer<>(10);

        Thread thread1 = new Thread(() -> {
            for(int i = 0; i < 101; i+=2) {
                System.out.println(Thread.currentThread() + " produced value: " + i);
                pc.produce(i);
            }
        }, "Thread 1");
        Thread thread2 = new Thread(() -> {
            for(int i = 1; i < 100; i+=2) {
                System.out.println(Thread.currentThread() + " produced value: " + i);
                pc.produce(i);
            }
        }, "Thread 2");

        Thread thread3 = new Thread(() -> {
            while (true) {
                Integer val = pc.consume();
                System.out.println(Thread.currentThread() + " consumed value: " + val);
            }
        }, "Thread 3");
        Thread thread4 = new Thread(() -> {
            while (true) {
                Integer val = pc.consume();
                System.out.println(Thread.currentThread() + " consumed value: " + val);
            }
        }, "Thread 4");
        Thread thread5 = new Thread(() -> {
            while (true) {
                Integer val = pc.consume();
                System.out.println(Thread.currentThread() + " consumed value: " + val);
            }
        }, "Thread 5");

        thread3.start();
        thread4.start();
        thread5.start();

        thread1.start();
        thread2.start();

    }
}
