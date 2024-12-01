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

public class Exercise7Test {

    @Test
    public void createOfferWithCF() {
        var future1 = CompletableFuture.supplyAsync(BankingPortalService::getCurrentCustomer);
        var future2 = future1.thenApplyAsync(c -> BankingPortalService.getSavingsData(c.id()));
        var future3 = future1.thenApplyAsync(c -> BankingPortalService.getLoansData(c.id()));

        var customer = future1
                .exceptionally(th -> {
                    throw new RuntimeException(th);
                })
                .join();

        record CustomerDetails(Customer customer, Savings savings, Loans loans) { }
        var future = future2
                .thenCombine(future3, ((savings, loans) -> new CustomerDetails(customer, (Savings) savings, (Loans) loans)))
                .thenApplyAsync(cd -> BankingPortalService.calculateOffer(cd.customer.id(), cd.savings.savingsAmount(), cd.loans.loansAmount()))
                .exceptionally(th -> {
                    throw new RuntimeException(th);
                });

        var offer = future.join();
        System.out.println("Offer: " + offer);
        assertTrue(offer instanceof Offer);
    }

    // TODO Exercise 7: Structured Concurrency - Nested scope
    // Use BankingPortalService.getCurrentCustomer() to get current customer.
    // Use BankingPortalService.getSavingsData() to get savings data and
    // use BankingPortalService.getLoansData() to get loans data parallely.
    // When they both are done, use BankingPortalService.calculateOffer() to get an offer.
    @Test
    public void createOfferWithSC() throws InterruptedException {
        assertTrue(false, "Exercise not completed");
        Thread.startVirtualThread(this::createOffer).join();
    }

    private void createOffer() {
        // ...
    }
}
