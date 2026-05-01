package Utar_Smart_Campus_Facility_Booking_and_Maintenance_Management_System;

import java.sql.*;

public class MaintenanceService{

	// MR01
	public String submitReport(MaintenanceReport m) {

	    String sql = "INSERT INTO maintenance (report_id, facility_id, description, image, status, assigned_to) VALUES (?, ?, ?, ?, ?, ?)";

	    try (Connection conn = DataBase.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {


	        String reportID = generateReportID();

	        stmt.setString(1, reportID);
	        stmt.setString(2, m.getFacilityID());
	        stmt.setString(3, m.getDescription());
	        stmt.setString(4, m.getImage());
	        stmt.setString(5, "Pending");
	        stmt.setString(6, null); 

	        int rows = stmt.executeUpdate();

	        if (rows > 0) {
	            return "Report submitted successfully! ID: " + reportID;
	        } else {
	            return "Submit failed!";
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        return "Error submitting report!";
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
public String generateReportID() {

    String sql = "SELECT report_id FROM maintenance ORDER BY CAST(SUBSTR(report_id,2) AS INTEGER) DESC LIMIT 1";

    try (Connection conn = DataBase.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        if (rs.next()) {
            String lastID = rs.getString("report_id");

            if (lastID != null && lastID.length() > 1) {
                int num = Integer.parseInt(lastID.substring(1));
                num++;
                return String.format("M%03d", num);
            }
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return "M001";
	}
}
