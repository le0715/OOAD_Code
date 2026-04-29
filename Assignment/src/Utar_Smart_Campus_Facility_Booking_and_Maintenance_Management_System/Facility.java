package Utar_Smart_Campus_Facility_Booking_and_Maintenance_Management_System;

public class Facility {
    private String facilityID;
    private String name;
    private String type;
    private boolean availability;

    public String getFacilityID() {
        return facilityID;
    }

    public void setFacilityID(String facilityID) {
        this.facilityID = facilityID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
    
    // Default constructor
    public Facility() {
    }

    // Parameterized constructor
    public Facility(String facilityID, String name, String type, boolean availability) {
        this.facilityID = facilityID;
        this.name = name;
        this.type = type;
        this.availability = availability;
    }
}