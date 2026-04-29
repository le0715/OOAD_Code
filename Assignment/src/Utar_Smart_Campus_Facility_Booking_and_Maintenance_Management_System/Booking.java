package Utar_Smart_Campus_Facility_Booking_and_Maintenance_Management_System;

import java.time.LocalDateTime;

public class Booking {
    private String bookingID;
    private String userID;
    private String facilityID;
    private LocalDateTime timeSlot;
    private String purpose;
    private String status;

    public String getBookingID() {
        return bookingID;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFacilityID() {
        return facilityID;
    }

    public void setFacilityID(String facilityID) {
        this.facilityID = facilityID;
    }

    public LocalDateTime getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(LocalDateTime timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    // Default constructor
    public Booking() {
    }

    // Parameterized constructor
    public Booking(String bookingID, String userID, String facilityID,
                   LocalDateTime timeSlot, String purpose, String status) {
        this.bookingID = bookingID;
        this.userID = userID;
        this.facilityID = facilityID;
        this.timeSlot = timeSlot;
        this.purpose = purpose;
        this.status = status;
    }
}