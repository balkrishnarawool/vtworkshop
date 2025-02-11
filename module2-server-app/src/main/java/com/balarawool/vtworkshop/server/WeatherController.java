package com.balarawool.vtworkshop.server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.balarawool.vtworkshop.server.ThreadUtil.logAndWait;

@RestController
public class WeatherController {

    @GetMapping("/weatherFromSource1")
    public static Weather getWeatherFromSource1(@RequestParam("city") String city) {
        logAndWait("getWeatherFromSource1");
        return new Weather("10 C");
    }

    @GetMapping("/weatherFromSource2")
    public static Weather getWeatherFromSource2(@RequestParam("city") String city) {
        logAndWait("getWeatherFromSource2");
        return new Weather("9 C");
    }

    @GetMapping("/weatherFromSource3")
    public static Weather getWeatherFromSource3(@RequestParam("city") String city) {
        logAndWait("getWeatherFromSource3");
        return new Weather("8 C");
    }

    public record Weather(String temperature) {}
}