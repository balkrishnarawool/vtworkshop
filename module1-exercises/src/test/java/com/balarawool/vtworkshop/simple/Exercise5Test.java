package com.balarawool.vtworkshop.simple;

import com.balarawool.vtworkshop.simple.EventService.Event;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class Exercise5Test {

    // TODO Exercise 5: Structured Concurrency - All tasks required.
    // Use EventService.reserveVenue() to create Venue.
    // Use EventService.bookHotel() to create Hotel.
    // Use EventService.buySupplies() to create Supplies.
    // Create Event by using the above venue, hotel and supplies.
    @Test
    public void createEvent() {
        fail("Exercise not completed");
        // try (var scope = ...) {
        // ...
        //    assertTrue(event instanceof Event);
        //} catch (InterruptedException | ExecutionException e) {
        //    e.printStackTrace();
        //}
    }
}
