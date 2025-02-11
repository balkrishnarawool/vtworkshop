package com.balarawool.vtworkshop.simple;

import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;

class Exercise4Test {

    // TODO Exercise 4: Enable virtual threads in a SpringBoot application
    // Go to ServerApplication (in module2-server-app) and enable virtual threads.
    @Test
    public void testGetThread() throws Exception {
        HttpResponse<String> response = ServerUtil.call("/thread");
        System.out.println(response.body());
        assertTrue(response.body().contains("VirtualThread"));
    }
}