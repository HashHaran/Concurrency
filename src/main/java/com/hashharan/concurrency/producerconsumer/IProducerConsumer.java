package com.hashharan.concurrency.producerconsumer;

public interface IProducerConsumer<O> {
    public void produce(O element);
    public O consume();
}
