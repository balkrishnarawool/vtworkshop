package com.balarawool.vtworkshop.simple;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.http.HttpResponse;
import java.util.Map;

public class NewBankingPortalService {

    public static Customer getCurrentCustomer() {
        try {
            LogUtil.log("Starting: getCurrentCustomer()");
            HttpResponse<String> response = ServerUtil.call("/current-customer");
            var customer = new ObjectMapper().readValue(response.body(), Customer.class);

            LogUtil.log("Done: getCurrentCustomer()");
            return customer;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static Savings getSavingsData(String customerId) {
        try {
            LogUtil.log("Starting: getSavingsData()");
            HttpResponse<String> response = ServerUtil.call("/savings", Map.of("customerId", customerId));
            var savings = new ObjectMapper().readValue(response.body(), Savings.class);

            LogUtil.log("Done: getSavingsData()");
            return savings;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static Loans getLoansData(String customerId) {
        try {
            LogUtil.log("Starting: getLoansData()");
            HttpResponse<String> response = ServerUtil.call("/loans", Map.of("customerId", customerId));
            var loans = new ObjectMapper().readValue(response.body(), Loans.class);

            LogUtil.log("Done: getLoansData()");
            return loans;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static Offer calculateOffer(String customerId, String savings, String loans) {
        try {
            LogUtil.log("Starting: getCurrentCustomer()");
            HttpResponse<String> response = ServerUtil.call("/offer", Map.of("id", customerId, "savings", savings, "loans", loans));
            var offer = new ObjectMapper().readValue(response.body(), Offer.class);

            LogUtil.log("Done: getCurrentCustomer()");
            return offer;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public record Customer(String id) {}
    public record Savings(String savingsAmount) {}
    public record Loans(String loansAmount) {}
    public record Offer(String offerDetails) {}
}