package com.balarawool.vtworkshop.simple;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.http.HttpResponse;

public class EventService {

    public static Venue reserveVenue() {
        try {
            LogUtil.log("Starting: reserveVenue()");
            HttpResponse<String> response = ServerUtil.call("/venue");
            var venue = new ObjectMapper().readValue(response.body(), Venue.class);

            LogUtil.log("Done: reserveVenue()");
            return venue;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static Hotel bookHotel() {
        try {
            LogUtil.log("Starting: bookHotel()");
            HttpResponse<String> response = ServerUtil.call("/hotel");
            var hotel = new ObjectMapper().readValue(response.body(), Hotel.class);

            LogUtil.log("Done: bookHotel()");
            return hotel;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static Supplies buySupplies() {
        try {
            LogUtil.log("Starting: buySupplies()");
            HttpResponse<String> response = ServerUtil.call("/supplies");
            var supplies = new ObjectMapper().readValue(response.body(), Supplies.class);

            LogUtil.log("Done: buySupplies()");
            return supplies;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public record Venue(String location) {}
    public record Hotel(String name) {}
    public record Supplies(String details) {}
    public record Event(Venue venue, Hotel hotel, Supplies supplies) {}
}