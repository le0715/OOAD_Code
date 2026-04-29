package Utar_Smart_Campus_Facility_Booking_and_Maintenance_Management_System;

public class MaintenanceReport {
    private String reportID;
    private String facilityID;
    private String description;
    private String image;
    private String status;

    public String getReportID() {
        return reportID;
    }

    public void setReportID(String reportID) {
        this.reportID = reportID;
    }

    public String getFacilityID() {
        return facilityID;
    }

    public void setFacilityID(String facilityID) {
        this.facilityID = facilityID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    // Default constructor
    public MaintenanceReport() {
    }

    // Parameterized constructor
    public MaintenanceReport(String reportID, String facilityID,
                             String description, String image, String status) {
        this.reportID = reportID;
        this.facilityID = facilityID;
        this.description = description;
        this.image = image;
        this.status = status;
    }
}