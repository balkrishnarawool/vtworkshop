package com.balarawool.vtworkshop.simple;

import com.balarawool.vtworkshop.simple.EventService.Event;
import com.balarawool.vtworkshop.simple.WeatherService.Weather;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class Exercise6Test {

    // TODO Exercise 6: Structured Concurrency - Only one task required.
    // Use WeatherService.getWeatherFromSource1() to get weather-info for Amsterdam from Source 1.
    // Use WeatherService.getWeatherFromSource2() to get weather-info for Amsterdam from Source 2.
    // Use WeatherService.getWeatherFromSource3() to get weather-info for Amsterdam from Source 3.
    // Use weather-info from the source that retrieves it fastest.
    @Test
    public void getWeatherC() {
         try (var scope = new StructuredTaskScope.ShutdownOnSuccess<Weather>()) {
             scope.fork(() -> WeatherService.getWeatherFromSource1("Amsterdam"));
             scope.fork(() -> WeatherService.getWeatherFromSource2("Amsterdam"));
             scope.fork(() -> WeatherService.getWeatherFromSource3("Amsterdam"));

             var weather = scope.join().result();

             assertTrue(weather instanceof Weather);
         } catch (InterruptedException | ExecutionException e) {
             e.printStackTrace();
         }
    }
}
