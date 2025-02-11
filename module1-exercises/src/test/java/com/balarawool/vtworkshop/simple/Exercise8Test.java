package com.balarawool.vtworkshop.simple;

import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.StructuredTaskScope.Joiner;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

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
        Thread.startVirtualThread(this::createOffer).join();
    }

    private void createOffer() {
         var customer = NewBankingPortalService.getCurrentCustomer();
         ScopedValue.where(Metadata.LOGGED_IN_USER, customer).run(() -> {
             var id = Metadata.LOGGED_IN_USER.get().id();
             try (var scope = StructuredTaskScope.open(Joiner.allSuccessfulOrThrow())) {
                 var task1 = scope.fork(() -> NewBankingPortalService.getSavingsData(id));
                 var task2 = scope.fork(() -> NewBankingPortalService.getLoansData(id));

                 scope.join();

                 var savings = task1.get();
                 var loans = task2.get();

                 var offer = NewBankingPortalService.calculateOffer(id, savings.savingsAmount(), loans.loansAmount());
                 System.out.println(offer);
             } catch (InterruptedException e) {
                 throw new RuntimeException(e);
             }
         });
    }
}
