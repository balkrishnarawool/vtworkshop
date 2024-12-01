package com.balarawool.vtworkshop.server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.balarawool.vtworkshop.server.ThreadUtil.logAndWait;

@RestController
public class EventController {

    @GetMapping("/venue")
    public static Venue reserveVenue() {
        logAndWait("reserveVenue");
        return new Venue("Amsterdam");
    }

    @GetMapping("/hotel")
    public static Hotel bookHotel() {
        logAndWait("bookHotel");
        return new Hotel("Hilton");
    }

    @GetMapping("/supplies")
    public static Supplies buySupplies() {
        logAndWait("buySupplies");
        return new Supplies("Stuff");
    }

    public record Venue(String location) {}
    public record Hotel(String name) {}
    public record Supplies(String details) {}
    public record Event(Venue venue, Hotel hotel, Supplies supplies) {}
}