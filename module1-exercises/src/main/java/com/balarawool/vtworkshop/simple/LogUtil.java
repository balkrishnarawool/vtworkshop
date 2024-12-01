package com.balarawool.vtworkshop.simple;

public class LogUtil {
    public static void log(String message) {
        System.out.println("Thread: " + getThreadName() + " " + message);
    }

    public static String getThreadName() {
        var th = Thread.currentThread();
        return th.isVirtual() ? th.toString() : th.getName();
    }
}
