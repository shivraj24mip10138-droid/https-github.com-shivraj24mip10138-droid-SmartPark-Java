package com.smartparking;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BillingServiceTest {
    private final BillingService billingService = new BillingService();

    @Test
    void carMinimumChargeIsOneHour() {
        assertEquals(40.0, billingService.calculateFee("CAR", 10));
    }

    @Test
    void bikeTwoHoursCharge() {
        assertEquals(40.0, billingService.calculateFee("BIKE", 120));
    }

    @Test
    void partialHourRoundsUp() {
        assertEquals(80.0, billingService.calculateFee("CAR", 61));
    }
}
