package com.hashharan.concurrency.producerconsumer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class SemaphoreProducerConsumer<O> implements IProducerConsumer<O>{

    Semaphore mutex = new Semaphore(1);
    Semaphore full;
    Semaphore empty = new Semaphore(0);
    Queue<O> container = new LinkedList<>();
    int capacity;

    public SemaphoreProducerConsumer(int capacity) {
        this.capacity = capacity;
        full = new Semaphore(capacity);
    }

    @Override
    public void produce(O element) {
        try {
            full.acquire();
            mutex.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        container.add(element);
        mutex.release();
        empty.release();
    }

    @Override
    public O consume() {
        try {
            empty.acquire();
            mutex.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        O element = container.poll();
        mutex.release();
        full.release();
        return element;
    }
}
