package Utar_Smart_Campus_Facility_Booking_and_Maintenance_Management_System;

import java.sql.*;

public class CreateTable {
    public static void main(String[] args) {
        
        // 1. DROP TABLE Strings (To ensure the schema updates)
        String dropMaintenance = "DROP TABLE IF EXISTS maintenance";
        String dropBooking = "DROP TABLE IF EXISTS booking";
        String dropFacility = "DROP TABLE IF EXISTS facility";
        String dropUsers = "DROP TABLE IF EXISTS users";

        // 2. CREATE TABLE Strings
        String sql1 = "CREATE TABLE users (" +
                "user_id TEXT PRIMARY KEY," +
                "name TEXT NOT NULL," +
                "email TEXT UNIQUE," +
                "password TEXT," +
                "role TEXT," +
                "contact_no TEXT)";
    	
        String sql2 = "CREATE TABLE facility (" +
                "facility_id TEXT PRIMARY KEY," +
                "name TEXT NOT NULL," +
                "type TEXT," +
                "schedule TEXT)";
        
        String sql3 = "CREATE TABLE booking (" +
                "booking_id TEXT PRIMARY KEY," +
                "user_id TEXT," +
                "facility_id TEXT," +
                "time_slot TEXT," +
                "purpose TEXT," +
                "status TEXT," +
                "FOREIGN KEY (user_id) REFERENCES users(user_id)," +
                "FOREIGN KEY (facility_id) REFERENCES facility(facility_id))";
        
        // Fixed the missing comma between image and status here
        String sql4 = "CREATE TABLE maintenance (" +
                "report_id TEXT PRIMARY KEY," +
                "facility_id TEXT," +
                "description TEXT," +
                "image TEXT," + 
                "status TEXT," +
                "assigned_to TEXT," +
                "FOREIGN KEY (facility_id) REFERENCES facility(facility_id))";
        
        try (Connection conn = DataBase.getConnection();
             Statement stmt = conn.createStatement()) {
        	
            // Execute Drops (Order matters because of Foreign Keys)
            stmt.execute(dropMaintenance);
            stmt.execute(dropBooking);
            stmt.execute(dropFacility);
            stmt.execute(dropUsers);
            System.out.println("Old tables dropped (Schema reset).");

            // Execute Creates
            stmt.execute(sql1);
            stmt.execute(sql2);
            stmt.execute(sql3);
            stmt.execute(sql4);
            
            System.out.println("New tables created successfully!");

        } catch (SQLException e) {
            System.err.println("Database Error: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}