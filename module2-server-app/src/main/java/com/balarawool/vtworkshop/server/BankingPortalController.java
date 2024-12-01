package com.balarawool.vtworkshop.server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.balarawool.vtworkshop.server.ThreadUtil.logAndWait;


@RestController
public class BankingPortalController {

    @GetMapping("/current-customer")
    public static Customer getCurrentCustomer() {
        logAndWait("getCurrentCustomer");
        return new Customer("1234");
    }

    @GetMapping("/savings")
    public static Savings getSavingsData(@RequestParam("customerId") String customerId) {
        logAndWait("getSavingsData");
        return new Savings("12345.67");
    }

    @GetMapping("loans")
    public static Loans getLoansData(@RequestParam("customerId") String customerId) {
        logAndWait("getLoansData");
        return new Loans("1234.56");
    }

    @GetMapping("offer")
    public static Offer calculateOffer(@RequestParam("id") String id, @RequestParam("savings") String savings, @RequestParam("loans") String loans) {
        logAndWait("calculateOffer");
        return new Offer("OfferDetails");
    }

    public record Customer(String id) {}
    public record Savings(String savingsAmount) {}
    public record Loans(String loansAmount) {}
    public record Offer(String offerDetails) {}
}
