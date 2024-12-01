package com.balarawool.vtworkshop.simple;

import com.balarawool.vtworkshop.simple.EventService.Event;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Exercise5Test {

    @Test
    public void createEventWithCF() {
        var future1 = CompletableFuture.supplyAsync(EventService::reserveVenue);
        var future2 = CompletableFuture.supplyAsync(EventService::bookHotel);
        var future3 = CompletableFuture.supplyAsync(EventService::buySupplies);

        var futureEvent = CompletableFuture.allOf(future1, future2, future3)
                .exceptionally(th -> {
                    throw new RuntimeException(th);
                })
                .thenApply(ignored -> {
                    var venue = future1.join();
                    var hotel = future2.join();
                    var supplies = future3.join();

                    return new Event(venue, hotel, supplies);
                });

        var event = futureEvent.join();
        assertTrue(event instanceof Event);
    }

    // TODO Exercise 5: Structured Concurrency - All tasks required.
    // Use EventService.reserveVenue() to create Venue.
    // Use EventService.bookHotel() to create Hotel.
    // Use EventService.buySupplies() to create Supplies.
    // Create Event by using the above venue, hotel and supplies.
    @Test
    public void createEventWithSC() {
        assertTrue(false, "Exercise not completed");
        // try (var scope = ...) {
        // ...
        //    assertTrue(event instanceof Event);
        //} catch (InterruptedException | ExecutionException e) {
        //    e.printStackTrace();
        //}
    }
}
