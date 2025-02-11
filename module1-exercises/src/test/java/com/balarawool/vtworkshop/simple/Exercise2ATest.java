package com.balarawool.vtworkshop.simple;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * IMPORTANT: DO NOT USE THIS EXERCISE.
 * The goal was to show that virtual threads use significantly less memory than platform threads.
 * But the way it is used below, it does not give precise and timely information about memory-usage and hence it is not a proper test.
 */
public class Exercise2ATest {

    private static Runtime RUNTIME = Runtime.getRuntime();

    @Disabled
    @Test
    public void virtualThreadMemory() throws InterruptedException {
        var vts = measureMemory2(() -> {
            var list = new ArrayList<Thread>();
            for (int i = 0; i < 1_000; i++) {
                list.add(Thread.ofVirtual().unstarted(this::doNothing));
            }
        });
        var pts = measureMemory2(() -> {
            var list = new ArrayList<Thread>();
            for (int i = 0; i < 1_000; i++) {
                list.add(Thread.ofPlatform().unstarted(this::doNothing));
            }
        });
        System.out.println(vts);
        System.out.println(pts);
    }

    private void doNothing() {
    }

    private long measureMemory2(Runnable runnable) throws InterruptedException {
        System.gc();
        Thread.sleep(100);
        var usedMemoryByVTsBefore = getMemoryUsed();
        runnable.run();
        Thread.sleep(100);
        var usedMemoryByVTsAfter = getMemoryUsed();

        return usedMemoryByVTsAfter - usedMemoryByVTsBefore;
    }

    private long getMemoryUsed() {
        return RUNTIME.totalMemory() - RUNTIME.freeMemory();
    }

}
