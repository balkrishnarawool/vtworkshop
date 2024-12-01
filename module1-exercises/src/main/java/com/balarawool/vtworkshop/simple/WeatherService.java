package com.balarawool.vtworkshop.simple;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.http.HttpResponse;
import java.util.Map;

public class WeatherService {

    public static Weather getWeatherFromSource1(String city) {
        try {
            LogUtil.log("Starting: getWeatherFromSource1()");
            HttpResponse<String> response = ServerUtil.call("/weatherFromSource1", Map.of("city", city));
            var weather = new ObjectMapper().readValue(response.body(), Weather.class);

            LogUtil.log("Done: getWeatherFromSource1()");
            return weather;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static Weather getWeatherFromSource2(String city) {
        try {
            LogUtil.log("Starting: getWeatherFromSource2()");
            HttpResponse<String> response = ServerUtil.call("/weatherFromSource2", Map.of("city", city));
            var weather = new ObjectMapper().readValue(response.body(), Weather.class);

            LogUtil.log("Done: getWeatherFromSource2()");
            return weather;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static Weather getWeatherFromSource3(String city) {
        try {
            LogUtil.log("Starting: getWeatherFromSource3()");
            HttpResponse<String> response = ServerUtil.call("/weatherFromSource3", Map.of("city", city));
            var weather = new ObjectMapper().readValue(response.body(), Weather.class);

            LogUtil.log("Done: getWeatherFromSource3()");
            return weather;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    public record Weather(String temperature) {}
}