package Utar_Smart_Campus_Facility_Booking_and_Maintenance_Management_System;

import java.sql.*;

public class AdminService {
	//AM01
	public String updateBookingStatus(String bookingID, String status) {
	    String sql = "UPDATE booking SET status=? WHERE booking_id=? AND status='Pending'";

	    try (Connection conn = DataBase.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setString(1, status); // Approved / Rejected
	        stmt.setString(2, bookingID);
	        
	        int rows = stmt.executeUpdate();
	        
	        if (rows > 0){
	        return "Booking " + status + " successfully!";
	        }else {
	        	return "Booking not found OR already processed.";
	        }
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "Error updating booking";
	    }
	}

    public String rejectBooking(String bookingID) {
        return "Rejected";
    }

//AM02
    public String updateFacilitySchedule(String facilityID, String schedule) {

        String sql = "UPDATE facility SET schedule=? WHERE facility_id=?";

        try (Connection conn = DataBase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, schedule);
            stmt.setString(2, facilityID);

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                return "Facility schedule updated successfully!";
            } else {
                return "Facility not found.";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "Update failed!";
        }
    }
//AM03
    public String assignTask(String reportID, String staffID) {

        String sql = "UPDATE maintenance SET assigned_to=?, status='In Progress' WHERE report_id=?";

        try (Connection conn = DataBase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, staffID);
            stmt.setString(2, reportID);

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                return "Task assigned to staff " + staffID + " successfully!";
            } else {
                return "Report not found.";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "Assignment failed!";
        }
    }
}
