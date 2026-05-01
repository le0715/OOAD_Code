package Utar_Smart_Campus_Facility_Booking_and_Maintenance_Management_System;

import java.sql.*;
import java.util.*;

public class UserService{
    //UM01
	public String registerUser(User u) {

	    String sql = "INSERT INTO users (user_id, name, email, password, role, contact_no) VALUES (?, ?, ?, ?, ?, ?)";

	    try (Connection conn = DataBase.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        
	        String userID = generateUserID();

	        stmt.setString(1, userID);
	        stmt.setString(2, u.getName());
	        stmt.setString(3, u.getEmail());
	        stmt.setString(4, u.getPassword());
	        stmt.setString(5, "student");
	        stmt.setString(6, u.getContactNo());

	        stmt.executeUpdate();

	        return "Registration successful! Your ID: " + userID;

	    } catch (Exception e) {
	        e.printStackTrace();
	        return "Registration failed!";
	    }
	}
    //UM02
    public boolean login(String email, String password) {
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";

        try (Connection conn = DataBase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    //UM03

    public void updateProfile(User user) {

        Scanner sc = new Scanner(System.in);

        System.out.println("\n===== UPDATE PROFILE =====");

        System.out.print("New Name: ");
        String name = sc.nextLine();

        System.out.print("New Email: ");
        String email = sc.nextLine();

        System.out.print("New Password: ");
        String password = sc.nextLine();

        System.out.print("New Contact No: ");
        String contact = sc.nextLine();

        String sql = "UPDATE users SET name=?, email=?, password=?, contact_no=? WHERE user_id=?";

        try (Connection conn = DataBase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.setString(4, contact);
            stmt.setString(5, user.getUserID());

            stmt.executeUpdate();

            // update current session object
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
            user.setContactNo(contact);

            System.out.println("Profile updated successfully!");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Update failed!");
        }
    }

public User loginUser(String email, String password) {

    String sql = "SELECT * FROM users WHERE email=? AND password=?";

    try (Connection conn = DataBase.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, email);
        stmt.setString(2, password);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            User u = new User();
            u.setUserID(rs.getString("user_id"));
            u.setName(rs.getString("name"));
            u.setEmail(rs.getString("email"));
            u.setRole(rs.getString("role"));
            return u;
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return null;
  }


public String generateUserID() {

	String sql = "SELECT user_id FROM users ORDER BY CAST(SUBSTR(user_id,2) AS INTEGER) DESC LIMIT 1";

    try (Connection conn = DataBase.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        if (rs.next()) {
            String lastID = rs.getString("user_id"); 

            int num = Integer.parseInt(lastID.substring(1));
            num++;

            return String.format("U%03d", num); 
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    return "U001";
	}
}
