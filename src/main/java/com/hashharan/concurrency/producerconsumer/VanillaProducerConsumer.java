package com.hashharan.concurrency.producerconsumer;

import java.util.LinkedList;
import java.util.Queue;

public class VanillaProducerConsumer<O> implements IProducerConsumer<O> {
    int capacity;
    Queue<O> container;

    public VanillaProducerConsumer(int capacity) {
        this.capacity = capacity;
        container = new LinkedList<O>();
    }

    @Override
    public void produce(O element) {
        synchronized (container) {
            while (container.size() == capacity){
                try {
                    container.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            container.add(element);
            container.notifyAll();
        }
    }

    @Override
    public O consume() {
        synchronized (container) {
            while (container.size() == 0){
                try {
                    container.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            O element = container.poll();
            container.notifyAll();
            return element;
        }
    }
}
