package com.balarawool.vtworkshop.simple;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class Exercise2Test {

    // TODO Exercise 2: Virtual threads are lightweight threads.
    // Create 1000 platform threads, each of which does Thread.sleep(1000);
    // Then increase this number to 5000 and then to 10000. You'll see OutOfMemoryError.
    // Change the threads to virtual threads and you can increase the number to 1 million or even beyond.
    public static void main(String[] args) throws InterruptedException {
        var list = new ArrayList<Thread>();
        for (int i = 0; i < 10_000_000; i++) {
            var t = Thread.ofVirtual().unstarted(() -> {
                try {
                    Thread.sleep(1_000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            list.add(t);
        }
        list.forEach(Thread::start);
    }
}
