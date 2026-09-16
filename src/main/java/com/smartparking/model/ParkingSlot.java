package com.smartparking.model;

public class ParkingSlot {
    private final int id;
    private final String slotNumber;
    private final String vehicleType;
    private final String status;

    public ParkingSlot(int id, String slotNumber, String vehicleType, String status) {
        this.id = id;
        this.slotNumber = slotNumber;
        this.vehicleType = vehicleType;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getSlotNumber() {
        return slotNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getStatus() {
        return status;
    }
}
