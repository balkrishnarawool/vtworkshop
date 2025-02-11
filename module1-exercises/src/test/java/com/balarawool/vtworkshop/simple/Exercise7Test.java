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
    // use BankingPortalService.getLoansData() to get loans data in-parallel.
    // When they both are done, use BankingPortalService.calculateOffer() to get an offer.
    @Test
    public void createOfferWithSC() throws InterruptedException {
        Thread.startVirtualThread(this::createOffer).join();
    }

    private void createOffer() {
        var customer = BankingPortalService.getCurrentCustomer();

        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            var task1 = scope.fork(() -> BankingPortalService.getSavingsData(customer.id()));
            var task2 = scope.fork(() -> BankingPortalService.getLoansData(customer.id()));

            scope.join().throwIfFailed();

            var savings = task1.get();
            var loans = task2.get();

            var offer = BankingPortalService.calculateOffer(customer.id(), savings.savingsAmount(), loans.loansAmount());
            System.out.println(offer);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
