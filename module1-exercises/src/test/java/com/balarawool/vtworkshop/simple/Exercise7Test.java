package com.balarawool.vtworkshop.simple;

import com.balarawool.vtworkshop.simple.BankingPortalService.Customer;
import com.balarawool.vtworkshop.simple.BankingPortalService.Loans;
import com.balarawool.vtworkshop.simple.BankingPortalService.Offer;
import com.balarawool.vtworkshop.simple.BankingPortalService.Savings;
import com.balarawool.vtworkshop.simple.EventService.Event;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class Exercise7Test {

    // TODO Exercise 7: Structured Concurrency - Nested scope
    // Use BankingPortalService.getCurrentCustomer() to get current customer.
    // Use BankingPortalService.getSavingsData() to get savings data and
    // use BankingPortalService.getLoansData() to get loans data parallely.
    // When they both are done, use BankingPortalService.calculateOffer() to get an offer.
    @Test
    public void createOfferWithSC() throws InterruptedException {
        fail("Exercise not completed");
        Thread.startVirtualThread(this::createOffer).join();
    }

    private void createOffer() {
        // ...
    }
}
