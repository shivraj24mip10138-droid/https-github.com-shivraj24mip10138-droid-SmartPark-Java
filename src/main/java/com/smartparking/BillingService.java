package com.smartparking;

public class BillingService {
    private static final double CAR_RATE_PER_HOUR = 40.0;
    private static final double BIKE_RATE_PER_HOUR = 20.0;

    public double calculateFee(String vehicleType, long durationMinutes) {
        long billableHours = Math.max(1, (durationMinutes + 59) / 60);
        double hourlyRate = "BIKE".equalsIgnoreCase(vehicleType)
                ? BIKE_RATE_PER_HOUR
                : CAR_RATE_PER_HOUR;
        return billableHours * hourlyRate;
    }
}
