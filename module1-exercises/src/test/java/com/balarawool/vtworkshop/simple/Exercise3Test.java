package com.balarawool.vtworkshop.simple;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Exercise3Test {

    // TODO Exercise 3: Few platform threads support large number of virtual threads.
    // This is because virtual threads don't block CPU or carrier thread.
    // Create an ExecutorService that gives virtual-threads on each request.
    // Use that to create 1000 virtual-threads, each thread calls task() method from this class.
    // Check that number of platform-threads is significantly lower than number of virtual-threads.
    @Test
    public void testVirtualThreadsCpuUsage() {
        var setVTs = new ConcurrentSkipListSet<String>();
        var setPTs = new ConcurrentSkipListSet<String>();
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 1000; i++) {
                executor.submit(() -> task(setVTs, setPTs));
            }
        }
        System.out.println(setPTs.size());
        System.out.println(setVTs.size());
        assertTrue(setPTs.size() < setVTs.size(), "Few platform threads support large number of virtual threads.");
    }

    private void task(Set<String> setVTs, Set<String> setPTs) {
        updateThreadSets(setVTs, setPTs);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        updateThreadSets(setVTs, setPTs);
    }

    private void updateThreadSets(Set<String> setVTs, Set<String> setPTs) {
        var thread = Thread.currentThread().toString();
        var vtName = thread.substring(0, thread.indexOf('/'));
        var ptName = thread.substring(thread.indexOf('@')+1);
        setVTs.add(vtName);
        setPTs.add(ptName);
    }
}
