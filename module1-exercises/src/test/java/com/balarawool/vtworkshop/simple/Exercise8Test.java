package com.balarawool.vtworkshop.simple;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Exercise8Test {

    // TODO Exercise 8: Scoped value with Structured Concurrency.
    // Uncomment code in method createOffer().
    // Set Metadata.LOGGED_IN_USER at the right place in createOffer().
    // Make it compile by removing first-argument (customerId) from these methods:
    //      - NewBankingPortalService.getSavingsData()
    //      - NewBankingPortalService.getLoansData()
    //      - NewBankingPortalService.calculateOffer()
    // In these methods, read the customer from Metadata.LOGGED_IN_USER and use customer.id() from there.
    @Test
    public void createOfferWithSC() throws InterruptedException {
        assertTrue(false, "Exercise not completed");
        Thread.startVirtualThread(this::createOffer).join();
    }

    private void createOffer() {
        // var customer = BankingPortalService.getCurrentCustomer();
        // Set Metadata.LOGGED_IN_USER here and call below code in run() method of ScopedValue:
        //    try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
        //            var task1 = scope.fork(() -> NewBankingPortalService.getSavingsData());
        //            var task2 = scope.fork(() -> NewBankingPortalService.getLoansData());
        //
        //            scope.join().throwIfFailed();
        //
        //            var savings = task1.get();
        //            var loans = task2.get();
        //
        //            var offer = NewBankingPortalService.calculateOffer(savings.savingsAmount(), loans.loansAmount());
        //            System.out.println(offer);
        //    } catch (InterruptedException | ExecutionException e) {
        //        throw new RuntimeException(e);
        //    }
    }
}
