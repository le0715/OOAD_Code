package Utar_Smart_Campus_Facility_Booking_and_Maintenance_Management_System;

import java.util.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BookingService {

// FB01
	    public List<Facility> searchFacilities(String type) {

	        List<Facility> list = new ArrayList<>();

	        String sql = "SELECT * FROM facility WHERE type LIKE ?";

	        try (Connection conn = DataBase.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {

	            stmt.setString(1,"%" + type + "%");

	            ResultSet rs = stmt.executeQuery();

	            while (rs.next()) {
	            	
	                Facility f = new Facility();
	                f.setFacilityID(rs.getString("facility_id"));
	                f.setName(rs.getString("name"));
	                f.setType(rs.getString("type"));

	                list.add(f);
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return list;
	    }
//FB02
	    	public String bookFacility(String userID, String facilityID, String time, String purpose) {

	        String sql = "INSERT INTO booking (booking_id, user_id, facility_id, time_slot, purpose, status) VALUES (?, ?, ?, ?, ?, ?)";

	        try (Connection conn = DataBase.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {

	            
	            String bookingID = generateBookingID();

	            stmt.setString(1, bookingID);
	            stmt.setString(2, userID);
	            stmt.setString(3, facilityID);
	            stmt.setString(4, time);
	            stmt.setString(5, purpose);
	            stmt.setString(6, "Pending");

	            int rows = stmt.executeUpdate();

	            if (rows > 0) {
	                return "Booking Successful! ID: " + bookingID;
	            } else {
	                return "Booking failed!";
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	            return "Error during booking!";
	        }
	    }
//FB03
	    public String modifyBooking(String bookingID,String newTime ,String newPurpose) {
	    String sql =	"UPDATE booking SET time_slot=?, purpose=? WHERE booking_id=?";

	    try (Connection conn = DataBase.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setString(1, newTime);
	        stmt.setString(2, newPurpose);
	        stmt.setString(3, bookingID);

	        int rows = stmt.executeUpdate();
	        
	        if(rows > 0) {
	        	return "Booking Updated";
	        }else {return "Booking ID not found";
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "Update Failed";
	    }
}
	    
	    public String cancelBooking(String bookingID) {

	        String sql = "UPDATE booking SET status = 'Cancelled' WHERE booking_id = ?";

	        try (Connection conn = DataBase.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {

	            stmt.setString(1, bookingID);
	            
	            int rows = stmt.executeUpdate();
	            if(rows >0) {
	            return "Booking Cancelled";
	            }else {
	            	return "Booking ID not found";
	            }
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	            return "Cancel Failed";
	        }
	    }
	    
//FB04

	    public String viewBookingStatus(String bookingID) {

	    	String sql = "SELECT * FROM booking WHERE booking_id = ?";

	        try (Connection conn = DataBase.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {

	            stmt.setString(1, bookingID);

	            ResultSet rs = stmt.executeQuery();

	            if (rs.next()) {
	                return "\nBooking ID: " + rs.getString("booking_id") +
	                        "\nFacility ID: " + rs.getString("facility_id") +
	                        "\nTime Slot: " + rs.getString("time_slot") +
	                        "\nPurpose: " + rs.getString("purpose") +
	                        "\nStatus: " + rs.getString("status");
	            }else {
	                return "Booking not found!";
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	            return "Error retrieving booking";
	        }
	    }
	    
//FB05
	    public void sendReminder(String userID) {
	        String sql = "SELECT * FROM booking WHERE user_id=?";

	        try (Connection conn = DataBase.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {

	            stmt.setString(1, userID);
	            ResultSet rs = stmt.executeQuery();

	            LocalDateTime now = LocalDateTime.now();
	            boolean found = false;

	            // Use a formatter that matches exactly how your data is stored in SQLite
	            // Based on your error, your format is "yyyy-MM-dd HH:mm"
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

	            while (rs.next()) {
	                // Get as String first to avoid the SQLite Timestamp Parser error
	                String timeSlotStr = rs.getString("time_slot");
	                
	                if (timeSlotStr == null || timeSlotStr.isEmpty()) continue;

	                try {
	                    // Parse the string manually
	                    LocalDateTime timeSlot = LocalDateTime.parse(timeSlotStr, formatter);

	                    if (timeSlot.isAfter(now)) {
	                        found = true;
	                        System.out.println("\n==============================");
	                        System.out.println("        REMINDER ALERT");
	                        System.out.println("==============================");
	                        System.out.println("Booking ID : " + rs.getString("booking_id"));
	                        System.out.println("Facility   : " + rs.getString("facility_id"));
	                        System.out.println("Time       : " + timeSlotStr);
	                        System.out.println("Status     : " + rs.getString("status"));
	                        System.out.println("==============================");
	                    }
	                } catch (Exception parseEx) {
	                    System.out.println("Skipping invalid date format in DB: " + timeSlotStr);
	                }
	            }

	            if (!found) {
	                System.out.println("No upcoming bookings found for User: " + userID);
	            }

	        } catch (Exception e) {
	            System.err.println("Error processing reminders: " + e.getMessage());
	            e.printStackTrace();
	        }
	    }    

public String generateBookingID() {

    String sql = "SELECT booking_id FROM booking ORDER BY CAST(SUBSTR(booking_id,2) AS INTEGER) DESC LIMIT 1";

    try (Connection conn = DataBase.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        if (rs.next()) {
            String lastID = rs.getString("booking_id");
            if (lastID != null && lastID.length() > 1) {
                int num = Integer.parseInt(lastID.substring(1)); 
                num++;
                return String.format("B%03d", num);
            }
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return "B001"; 
	}
}
