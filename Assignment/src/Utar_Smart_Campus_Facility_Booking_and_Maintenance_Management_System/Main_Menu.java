package Utar_Smart_Campus_Facility_Booking_and_Maintenance_Management_System;

import java.util.*;
import java.sql.*;	
import java.time.*;

public class Main_Menu {

    public static void main(String[] args) {
    	
        Scanner sc = new Scanner(System.in);
        UserService userService = new UserService();
        BookingService bookingService = new BookingService();
        AdminService adminService = new AdminService();
        MaintenanceService report = new MaintenanceService();
        AnalyticsService analyticsService = new AnalyticsService();
        

        while (true) {

            System.out.println("\n===== FACILITY SYSTEM =====");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Choose: ");

            int choice = sc.nextInt();
            sc.nextLine();
            
            switch (choice) {
            
            case 1: 
            	loginMenu(sc, userService, bookingService,report, adminService , analyticsService);
            	break;
            case 2:
                registerMenu(sc, userService);
                break;
            case 3:
                System.out.println("Exiting...");
                return;
                }
        	}
         }

    public static void loginMenu(Scanner sc, UserService userService,BookingService bookingService,
    		MaintenanceService report,AdminService adminService , AnalyticsService analyticsService) {

System.out.print("Email: ");
String email = sc.nextLine();

System.out.print("Password: ");
String password = sc.nextLine();

User user = userService.loginUser(email, password);

if (user != null) {

	System.out.println("Login Successful! Role: " + user.getRole());

	if (user.getRole().equalsIgnoreCase("student")) {
		studentMenu(sc, user, bookingService ,userService,report);
	}	 
	else {
		adminMenu(sc, user,adminService, bookingService, userService, analyticsService);
		}

	} 	
	else {
		System.out.println("Invalid login!");
	}
}
    
    public static void registerMenu(Scanner sc, UserService userService) {

        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        System.out.print("Role (student/admin/staff): ");
        String role = sc.nextLine();
        
        System.out.print("Contact No: ");
        String contactNo = sc.nextLine();

        User u = new User();
        u.setName(name);
        u.setEmail(email);
        u.setPassword(password);
        u.setRole(role);
        u.setContactNo(contactNo);

        System.out.println(userService.registerUser(u));
    }
    
    
public static void studentMenu(Scanner sc, User user, BookingService bookingService , UserService userService , MaintenanceService report) {

    while (true) {

    	System.out.println("\n===== STUDENT AND STAFF MENU =====");
        System.out.println("1. Search Facility");
        System.out.println("2. Book Facility");
        System.out.println("3. Modify or Cancel Booking");
        System.out.println("4. View Booking Status");
        System.out.println("5. Update Profile");
        System.out.println("6. Notification");
        System.out.println("7. Submit Maintenance Report");
        System.out.println("8. View Report Status");
        System.out.println("9. Logout");

        System.out.print("Choose: ");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {

        case 1:

            System.out.print("Enter Facility Type (Study/Event/Sports/etc): ");
            String type = sc.nextLine();

            List<Facility> facilities = bookingService.searchFacilities(type);

            if (facilities.isEmpty()) {
                System.out.println("No facilities found!");
            } else {
                System.out.println("\nAvailable Facilities:");
                for (Facility f : facilities) {
                    System.out.println(
                        f.getFacilityID() + " | " +
                        f.getName() + " | " +
                        f.getType()
                    );
                }
            }
            break;
            case 2:
                System.out.print("Booking ID(B000): ");
                String bid = sc.nextLine();

                System.out.print("Facility ID(F000): ");
                String fid = sc.nextLine();

                System.out.print("Purpose: ");
                String purpose = sc.nextLine();
                System.out.print("Time Slot (yyyy-mm-dd hh:mm): ");
                String time = sc.nextLine();
                System.out.println(bookingService.bookFacility(bid,user.getUserID(),fid,time,purpose));
                break;

            case 3:

                System.out.println("1. Modify Booking");
                System.out.println("2. Cancel Booking");

                int option = sc.nextInt();
                sc.nextLine();

                if (option == 1) {

                    System.out.print("Booking ID(B000): ");
                    String id = sc.nextLine();

                    System.out.print("New Time Slot (2026-05-01): ");
                    String time1 = sc.nextLine();

                    System.out.print("New Purpose: ");
                    String purpose1 = sc.nextLine();

                    System.out.println(bookingService.modifyBooking(id, time1, purpose1));

                } else if (option == 2) {

                    System.out.print("Booking ID(B000): ");
                    String id = sc.nextLine();

                    System.out.println(bookingService.cancelBooking(id));
                }

                break;
            case 4:
            	System.out.print("Enter Booking ID: ");
                String id = sc.nextLine();

                System.out.println(bookingService.viewBookingStatus(id));
                break;
                
            case 5:
            	userService.updateProfile(user);
            	break;
                
            case 6:
            	bookingService.sendReminder(user.getUserID());
                break;
            case 7:
                System.out.print("Report ID (M000): ");
                String rid = sc.nextLine();

                System.out.print("Facility ID: ");
                String fid1 = sc.nextLine();

                System.out.print("Issue Description: ");
                String desc = sc.nextLine();

                System.out.print("Image Path (optional): ");
                String img = sc.nextLine();

                MaintenanceReport m= new MaintenanceReport(rid, fid1, desc, img, "Pending");

                MaintenanceService ms = new MaintenanceService();

                System.out.println(ms.submitReport(m));

                break;
            case 8:
            	System.out.print("Enter Report ID: ");
            	String rid1 = sc.nextLine();
            	
            	MaintenanceService ms1 = new MaintenanceService();
            	System.out.println(ms1.trackStatus(rid1));
            	break;
            case 9:
                return;
        }
    }
 }


public static void adminMenu(Scanner sc, User user, AdminService adminService, BookingService bookingService , UserService userService,AnalyticsService analyticsService) {

    while (true) {

        System.out.println("\n===== ADMIN MENU =====");
        System.out.println("1. Approve Booking");
        System.out.println("2. Reject Booking");
        System.out.println("3. Manage Facility Schedule");
        System.out.println("4. Assign Maintenance Task");
        System.out.println("5. Update Profile");
        System.out.println("6. Generate Report");
        System.out.println("7. Identify Peak Hours Report");
        System.out.println("8. Maintenance Statics Report");
        System.out.println("9. Detect Problematic Facilities");
        System.out.println("10. Logout");

        System.out.print("Choose: ");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {

            case 1:
                System.out.print("Booking ID: ");
                String approveId = sc.nextLine();
                System.out.println(adminService.updateBookingStatus(approveId, "Approved"));
                break;

            case 2:
                System.out.print("Booking ID: ");
                String rejectId = sc.nextLine();
                System.out.println(adminService.updateBookingStatus(rejectId, "Rejected"));
                break;

            case 3:

                System.out.print("Facility ID: ");
                String fid = sc.nextLine();

                System.out.print("New Schedule: ");
                String schedule = sc.nextLine();

                System.out.println(adminService.updateFacilitySchedule(fid, schedule));

                break;
            case 4:
                System.out.print("Report ID(M000): ");
                String rid = sc.nextLine();

                System.out.print("Staff Name: ");
                String sid = sc.nextLine();

                System.out.println(adminService.assignTask(rid, sid));

                break;
            case 5:
            	userService.updateProfile(user);
            	break;
            case 6:
            	System.out.print("Start Date (yyyy-MM-dd HH:mm:ss): ");
                String start = sc.nextLine();

                System.out.print("End Date (yyyy-MM-dd HH:mm:ss): ");
                String end = sc.nextLine();
                
                analyticsService.generateUsageReport(start,end);
                break;
            case 7:
            	analyticsService.identifyPeakHours();
            	break;
            case 8:
                analyticsService.generateMaintenanceStatistics();
                break;
            case 9:
            	analyticsService.detectProblematicFacilities();
                break;
            case 10:
                return;
        }
    }
  }
}