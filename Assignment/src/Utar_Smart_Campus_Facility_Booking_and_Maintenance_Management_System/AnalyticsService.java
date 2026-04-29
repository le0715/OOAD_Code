package Utar_Smart_Campus_Facility_Booking_and_Maintenance_Management_System;

import java.sql.*;

public class AnalyticsService {
//AR01
	public void generateUsageReport(String startDate, String endDate) {

	    String sql = "SELECT facility_id, COUNT(*) AS total " +"FROM booking " +
	                 "WHERE time_slot BETWEEN ? AND ? " +"GROUP BY facility_id";

	    try (Connection conn = DataBase.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setString(1, startDate);
	        stmt.setString(2, endDate);

	        ResultSet rs = stmt.executeQuery();

	        System.out.println("\n==============================");
	        System.out.println("     USAGE REPORT");
	        System.out.println("==============================");

	        boolean found = false;

	        while (rs.next()) {
	            found = true;

	            System.out.println("Facility ID : " + rs.getString("facility_id"));
	            System.out.println("Total Usage : " + rs.getInt("total"));
	            System.out.println("------------------------------");
	        }

	        if (!found) {
	            System.out.println("No data found for this period.");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
//AR02
	public void identifyPeakHours() {

	    String sql = "SELECT substr(time_slot, 12, 2) AS hour, COUNT(*) AS total " +
	                 "FROM booking " +
	                 "GROUP BY hour " +
	                 "ORDER BY total DESC";

	    try (Connection conn = DataBase.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {

	        System.out.println("\n==============================");
	        System.out.println("     PEAK HOUR REPORT");
	        System.out.println("==============================");

	        boolean found = false;

	        while (rs.next()) {
	            found = true;

	            System.out.println("Hour      : " + rs.getString("hour") + ":00");
	            System.out.println("Bookings  : " + rs.getInt("total"));
	            System.out.println("------------------------------");
	        }

	        if (!found) {
	            System.out.println("No booking data available!");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
//AR03
	public void generateMaintenanceStatistics() {

	    String sql = "SELECT status, COUNT(*) AS total FROM maintenance GROUP BY status";

	    try (Connection conn = DataBase.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {

	        System.out.println("\n==============================");
	        System.out.println("  MAINTENANCE STATISTICS REPORT");
	        System.out.println("==============================");

	        boolean found = false;

	        while (rs.next()) {
	            found = true;

	            System.out.println("Status : " + rs.getString("status"));
	            System.out.println("Total  : " + rs.getInt("total"));
	            System.out.println("------------------------------");
	        }

	        if (!found) {
	            System.out.println("No maintenance data found!");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


//AR04
public void detectProblematicFacilities() {

    String sql = "SELECT facility_id, COUNT(*) AS total " +
                 "FROM maintenance " +
                 "GROUP BY facility_id " +
                 "HAVING total >= 1";

    try (Connection conn = DataBase.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        System.out.println("\n==============================");
        System.out.println("  ALERT: PROBLEMATIC FACILITIES");
        System.out.println("==============================");

        boolean found = false;

        while (rs.next()) {
            found = true;

            System.out.println("Facility ID : " + rs.getString("facility_id"));
            System.out.println("Issues      : " + rs.getInt("total"));
            System.out.println("STATUS      : FLAGGED ⚠");
            System.out.println("------------------------------");
        }

        if (!found) {
            System.out.println("No problematic facilities detected.");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
  }
}