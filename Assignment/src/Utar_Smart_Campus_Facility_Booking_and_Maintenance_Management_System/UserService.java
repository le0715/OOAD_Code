package Utar_Smart_Campus_Facility_Booking_and_Maintenance_Management_System;

import java.sql.*;
import java.util.*;

public class UserService{
    //UM01
	public String registerUser(User u) {
	  if (u.getEmail() == null || !u.getEmail().toLowerCase().endsWith("@1utar.my")) {
        return "Registration failed! Invalid email domain. Use @1utar.my";
    }
	   try (FileWriter fw = new FileWriter("users.txt", true)) {

        String id = generateUserID();

        fw.write(id + "," +
                 u.getName() + "," +
                 u.getEmail() + "," +
                 u.getPassword() + "," +
                 "student," +
                 u.getContactNo() + "\n");

        return "Registered! ID: " + id;

    } catch (Exception e) {
        return "Error!";
    }
}
    //UM02
public User loginUser(String email, String password) {

    try (BufferedReader br = new BufferedReader(new FileReader("users.txt"))) {

        String line;

        while ((line = br.readLine()) != null) {
            String[] d = line.split(",");

            if (d[2].equals(email) && d[3].equals(password)) {
                User u = new User();
                u.setUserID(d[0]);
                u.setName(d[1]);
                u.setRole(d[4]);
                return u;
            }
        }

    } catch (Exception e) {}

    return null;
}
    //UM03

public void updateProfile(User user, String name, String email, String pass, String contact) {

    try {
        File input = new File("users.txt");
        File temp = new File("temp.txt");

        BufferedReader br = new BufferedReader(new FileReader(input));
        FileWriter fw = new FileWriter(temp);

        String line;

        while ((line = br.readLine()) != null) {

            String[] d = line.split(",");

            if (d[0].equals(user.getUserID())) {
                fw.write(d[0] + "," + name + "," + email + "," + pass + "," + d[4] + "," + contact + "\n");
            } else {
                fw.write(line + "\n");
            }
        }

        br.close();
        fw.close();

        input.delete();
        temp.renameTo(input);

    } catch (Exception e) {}
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
