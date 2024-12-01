package com.balarawool.vtworkshop.simple;

import com.balarawool.vtworkshop.simple.EventService.Event;
import com.balarawool.vtworkshop.simple.WeatherService.Weather;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Exercise6Test {

    @Test
    public void getWeatherWithCF() {
        var future1 = CompletableFuture.supplyAsync(() -> WeatherService.getWeatherFromSource1("Amsterdam"));
        var future2 = CompletableFuture.supplyAsync(() -> WeatherService.getWeatherFromSource2("Amsterdam"));
        var future3 = CompletableFuture.supplyAsync(() -> WeatherService.getWeatherFromSource3("Amsterdam"));

        var weather = CompletableFuture.anyOf(future1, future2, future3)
                .exceptionally(th -> {
                    throw new RuntimeException(th);
                })
                .join();
        System.out.println(weather);
        assertTrue(weather instanceof Weather);
    }

    // TODO Exercise 6: Structured Concurrency - Only one task required.
    // Use WeatherService.getWeatherFromSource1() to get weather-info for Amsterdam from Source 1.
    // Use WeatherService.getWeatherFromSource2() to get weather-info for Amsterdam from Source 2.
    // Use WeatherService.getWeatherFromSource3() to get weather-info for Amsterdam from Source 3.
    // Use weather-info from the source that retrieves it fastest.
    @Test
    public void getWeatherWithSC() {
        assertTrue(false, "Exercise not completed");
        // try (var scope = ...) {
        //     ...
        //    assertTrue(weather instanceof Weather);
        // } catch (InterruptedException | ExecutionException e) {
        //   e.printStackTrace();
        // }
    }
}
