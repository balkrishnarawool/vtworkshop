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
         try (var scope = StructuredTaskScope.open()) {
             var task1 = scope.fork(() -> EventService.reserveVenue());
             var task2 = scope.fork(() -> EventService.bookHotel());
             var task3 = scope.fork(() -> EventService.buySupplies());

             scope.join();

             var venue = task1.get();
             var hotel = task2.get();
             var supplies = task3.get();
             var event = new Event(venue, hotel, supplies);

            assertTrue(event instanceof Event);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
