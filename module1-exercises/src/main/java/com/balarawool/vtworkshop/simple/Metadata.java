package com.balarawool.vtworkshop.simple;

import com.balarawool.vtworkshop.simple.BankingPortalService.Customer;

public class Metadata {
    public static ScopedValue<Customer> LOGGED_IN_USER = ScopedValue.newInstance();
}
