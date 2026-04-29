package Utar_Smart_Campus_Facility_Booking_and_Maintenance_Management_System;

import java.sql.*;

public class MaintenanceService {

	// MR01
    public String submitReport(MaintenanceReport report) {
    	   String sql = "INSERT INTO maintenance (report_id, facility_id, description, image ,status) VALUES (?, ?, ?, ?)";

    	    try (Connection conn = DataBase.getConnection();
    	         PreparedStatement stmt = conn.prepareStatement(sql)) {

    	        stmt.setString(1, report.getReportID());
    	        stmt.setString(2, report.getFacilityID());
    	        stmt.setString(3, report.getDescription());
    	        stmt.setString(4, report.getImage());
    	        stmt.setString(5, "Pending");

    	        stmt.executeUpdate();
    	        
    	        return "Report submitted";

    	    } catch (Exception e) {
    	        e.printStackTrace();
    	        return "Report submission failed";
    	    }
    	}
    //MR02
    public String trackStatus(String reportID) {
        String sql = "SELECT status FROM maintenance WHERE report_id=?";

        try (Connection conn = DataBase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, reportID);
            
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return "Report Status: " + rs.getString("status");
            }else {
            	return "Report ID not found";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Error retrieving status";
    }
}