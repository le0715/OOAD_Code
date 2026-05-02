package Utar_Smart_Campus_Facility_Booking_and_Maintenance_Management_System;

import java.sql.*;

public class InsertTestData {

    public static void main(String[] args) {
    	  try {
    		    clearTable("users");
    		    clearTable("facility");
    		    clearTable("booking");
    		    clearTable("maintenance");
    		    insertUsers();
    	        insertFacility();
    	        insertBooking();
    	        insertMaintenance();
    	        
    	        System.out.println("ALL TEST DATA INSERTED SUCCESSFULLY!");

    	    } catch (Exception e) {
    	        e.printStackTrace();
    	    }
    }


    // ================= USERS =================
    public static void insertUsers() {

        String sql = "INSERT INTO users VALUES (?, ?, ?, ?, ?, ?)";  //user_id , name , email , password , role , contact_no

        try (Connection conn = DataBase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "U001");
            stmt.setString(2, "Ali");
            stmt.setString(3, "ali@gmail.com");
            stmt.setString(4, "1234");
            stmt.setString(5, "student");
            stmt.setString(6, "0123456789");
            stmt.executeUpdate();

            stmt.setString(1, "U002");
            stmt.setString(2, "Siti");
            stmt.setString(3, "siti@gmail.com");
            stmt.setString(4, "1234");
            stmt.setString(5, "student");
            stmt.setString(6, "0112233445");
            stmt.executeUpdate();

            stmt.setString(1, "A001");
            stmt.setString(2, "Admin");
            stmt.setString(3, "admin@gmail.com");
            stmt.setString(4, "admin123");
            stmt.setString(5, "admin");
            stmt.setString(6, "0199988877");
            stmt.executeUpdate();

            System.out.println("Users inserted!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= FACILITY =================
    public static void insertFacility() {

    	String sql = "INSERT INTO facility VALUES (?, ?, ?, ?)"; //facility_id , name , schedule,type

        try (Connection conn = DataBase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "F001");
            stmt.setString(2, "Room A");
            stmt.setString(3, "Study");
            stmt.setString(4, "Mon-Fri 9AM-5PM");
            stmt.executeUpdate();

            stmt.setString(1, "F002");
            stmt.setString(2, "Room B");
            stmt.setString(3, "Study");
            stmt.setString(4, "Mon-Fri 8AM-5PM");
            stmt.executeUpdate();

            stmt.setString(1, "F003");
            stmt.setString(2, "Hall 1");
            stmt.setString(3, "Event");
            stmt.setString(4, "Mon-Fri 9AM-9PM");
            stmt.executeUpdate();

            stmt.setString(1, "F004");
            stmt.setString(2, "Lab 1");
            stmt.setString(3, "Computer");
            stmt.setString(4, "Mon-Fri 9AM-6PM");
            stmt.executeUpdate();

            stmt.setString(1, "F005");
            stmt.setString(2, "Court A");
            stmt.setString(3, "Sports");
            stmt.setString(4, "Mon-Fri 9AM-8PM");
            stmt.executeUpdate();

            System.out.println("Facilities inserted!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= BOOKING =================
    public static void insertBooking() {

        String sql = "INSERT INTO booking VALUES (?, ?, ?, ?, ?, ?)"; //booking_id , user_id, facility_id, time_slot,purpose,status

        try (Connection conn = DataBase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "B001");
            stmt.setString(2, "U001");
            stmt.setString(3, "F001");
            stmt.setString(4, "2026-05-01 10:00");
            stmt.setString(5, "Group Study");
            stmt.setString(6, "Pending");
            stmt.executeUpdate();		

            stmt.setString(1, "B002");
            stmt.setString(2, "U002");
            stmt.setString(3, "F002");
            stmt.setString(4, "2026-05-01 14:00");
            stmt.setString(5, "Meeting");
            stmt.setString(6, "Approved");
            stmt.executeUpdate();

            stmt.setString(1, "B003");
            stmt.setString(2, "U001");
            stmt.setString(3, "F003");
            stmt.setString(4, "2026-05-02 09:00");
            stmt.setString(5, "Club Event");
            stmt.setString(6, "Rejected");
            stmt.executeUpdate();

            System.out.println("Bookings inserted!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= MAINTENANCE =================
    public static void insertMaintenance() {
        String sql = "INSERT INTO maintenance (report_id, facility_id, description, image, status,assigned_to) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DataBase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "M001");
            stmt.setString(2, "F001");
            stmt.setString(3, "Aircond not working");
            stmt.setString(4, null); 
            stmt.setString(5, "Pending");
            stmt.setString(6, null);
            stmt.executeUpdate();

            stmt.setString(1, "M002");
            stmt.setString(2, "F002");
            stmt.setString(3, "Broken chair");
            stmt.setString(4, null); 
            stmt.setString(5, "In Progress");
            stmt.setString(6, null);
            stmt.executeUpdate();

            stmt.setString(1, "M003");
            stmt.setString(2, "F003");
            stmt.setString(3, "Projector issue");
            stmt.setString(4, null);
            stmt.setString(5, "Completed");
            stmt.setString(6, null);
            stmt.executeUpdate();
            
            stmt.setString(1, "M004");
            stmt.setString(2, "F001");
            stmt.setString(3, "Light Issue");
            stmt.setString(4, null); 
            stmt.setString(5, "Pending");
            stmt.setString(6, null);
            stmt.executeUpdate();
            
            stmt.setString(1, "M005");
            stmt.setString(2, "F001");
            stmt.setString(3, "Fan not working");
            stmt.setString(4, null); 
            stmt.setString(5, "Pending");
            stmt.setString(6, null);
            stmt.executeUpdate();
            
            System.out.println("Maintenance inserted!");

        } catch (Exception e) {
            System.err.println("Error inserting maintenance records: " + e.getMessage());
            e.printStackTrace();
        }
    }
	
	
	public static void clearTable(String table) {
	
	    String sql = "DELETE FROM " + table;

    try (Connection conn = DataBase.getConnection();
         Statement stmt = conn.createStatement()) {

        stmt.executeUpdate(sql);

    } catch (Exception e) {
        e.printStackTrace();
    }
  }
}
