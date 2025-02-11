package com.balarawool.vtworkshop.simple;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class Exercise1Test {

    // TODO Exercise 1.1: Create platform thread that prints current-thread (Thread.currentThread()).
    @Test
    public void createPlatformThread() {
        Thread t = Thread.ofPlatform().start(() -> System.out.println(Thread.currentThread()));
        assertFalse(t.isVirtual());
    }

    // TODO Exercise 1.2: Create virtual thread that prints current thread.
    @Test
    public void createVirtualThread() {
        Thread t = Thread.ofVirtual().start(() -> System.out.println(Thread.currentThread()));
        assertTrue(t.isVirtual());
    }

    // TODO Exercise 1.3: Create an ExecutorService that creates virtual threads on each request and
    //  use it to create 10 virtual-threads.
    @Test
    public void createVirtualThreadsUsingExecutor() {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 10; i++) {
                executor.submit(() -> System.out.println(Thread.currentThread()));
            }
            assertTrue(executor instanceof ExecutorService);
        }
    }
}
