package User;

import java.util.List;
import java.sql.*;
import java.util.Scanner;

public class Main {

    private static final String url = "jdbc:postgresql://localhost:5434/user";
    private static final String username = "postgres";
    private static final String password = "aldebaran1711";

    private static Scanner scanner = new Scanner(System.in);
    private static UserDAO userDAO;  // Class-level variable

    public static void main(String[] args) {

        try (Connection connection = DriverManager.getConnection(url, username, password)) {

            // Initialize the userDAO object at the class level
            userDAO = new UserDAO(connection);
            System.out.println("User Management System");
            System.out.println("Enter your name: ");
            String username = scanner.nextLine();
            System.out.println("WELCOME " + username + "!");

            while (true) {
                displayOption();
                System.out.println("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        addUserDetails();
                        break;
                    case 2:
                        listUserDetails();
                        break;
                    case 3:
                        listUserDetailsByID();
                        break;
                    case 4:
                        updateUserDetails();
                        break;
                    case 5:
                        deleteUserDetails();
                        break;
                    default:
                        System.out.println("Invalid choice, please try again.");
                }
            }

        } catch (SQLException ie) {
            ie.printStackTrace();
        }
    }

    private static void displayOption() {
        System.out.println("Please select option below");
        System.out.println("--------------------------");
        System.out.println("1) Add user details");
        System.out.println("2) List user details");
        System.out.println("3) List user details by ID");
        System.out.println("4) Update user details");
        System.out.println("5) Delete user details");
        System.out.println("---------------------------");
    }

    public static void addUserDetails() {
        try {
            System.out.println("Enter ID: ");
            int ID = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Enter name: ");
            String name = scanner.nextLine();

            System.out.println("Enter email: ");
            String email = scanner.nextLine();

            System.out.println("Enter age: ");
            int age = scanner.nextInt();
            scanner.nextLine();  // To consume newline left after nextInt()

            System.out.println("Enter role (Admin/User/Guest): ");
            String role = scanner.nextLine().trim(); // Trim any extra spaces

            // Debugging output to see what the input is
            System.out.println("Input Role: " + role);

            try {
                Role roleType = Role.valueOf(role.toUpperCase()); // Convert input to uppercase and map to enum
                if (roleType.equals(Role.ADMIN)) {
                    User adminRole = new AdminRole(ID, name, email, age);
                    userDAO.InsertUser(adminRole);
                } else if (roleType.equals(Role.USER)) {
                    User userRole = new UserRole(ID, name, email, age);
                    userDAO.InsertUser(userRole);
                } else if (roleType.equals(Role.GUEST)) {
                    User guestRole = new GuestRole(ID, name, email, age);
                    userDAO.InsertUser(guestRole);
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid role. Please enter a valid role (Admin/User/Guest).");
            }

        } catch (SQLException ie) {
            ie.printStackTrace();
            System.out.println("Failed to insert user");
        }
    }

    public static List<User> listUserDetails() {
        try {
            List<User> userList = userDAO.listUser();
            if (userList.isEmpty()) {
                System.out.println("The list is empty");
            } else {
                for (User user : userList) {
                    System.out.println(user);
                }
            }
        } catch (SQLException ie) {
            ie.printStackTrace();
            System.out.println("Failed to retrieve user list");
        }

        return null;
    }

    public static List<User> listUserDetailsByID() {
        try {
            System.out.println("Enter user ID you want to see: ");
            int ID = scanner.nextInt();
            scanner.nextLine();

            User userListID = userDAO.listUserByID(ID);

            if (userListID != null) {
                System.out.println(userListID);
            } else {
                System.out.println("Failed to get user details by ID");
            }
        } catch (SQLException ie) {
            ie.printStackTrace();
            System.out.println("Failed to retrieve user list");
        }

        return null;
    }

    public static void updateUserDetails() {
        try {
            System.out.println("Enter user ID you want to update: ");
            int userID = scanner.nextInt();
            scanner.nextLine();

            User userUpdate = userDAO.listUserByID(userID);
            if (userUpdate != null) {
                System.out.println("Current user name: " + userUpdate.getName());
                System.out.println("Enter new user name: ");
                String username = scanner.nextLine();
                if (!username.isEmpty()) {
                    userUpdate.setName(username);
                }

                System.out.println("Current user email: " + userUpdate.getEmail());
                System.out.println("Enter new user email: ");
                String email = scanner.nextLine();
                if (!email.isEmpty()) {
                    userUpdate.setEmail(email);
                }

                System.out.println("Current user age: " + userUpdate.getAge());
                System.out.println("Enter new user age: ");
                String age = scanner.nextLine();
                if (!age.isEmpty()) {
                    int ageInt = Integer.parseInt(age);
                    userUpdate.setAge(ageInt);
                }

                System.out.println("Current user role: " + userUpdate.getRole());
                System.out.println("Enter new user role: ");
                String roleString = scanner.nextLine();
                if (!roleString.isEmpty()) {
                    Role userType = Role.valueOf(roleString.toUpperCase());
                    userUpdate.setRole(userType);
                }

                // Call the update method
                userDAO.UpdateUser(userUpdate);
                System.out.println("User details updated successfully.");
            }
        } catch (SQLException ie) {
            ie.printStackTrace();
            System.out.println("Failed to update user list");
        }
    }

    public static void deleteUserDetails() {
        try {
            System.out.println("Enter ID of user details you want to delete: ");
            int deleteID = scanner.nextInt();
            userDAO.DeleteUser(deleteID);
        } catch (SQLException ie) {
            ie.printStackTrace();
            System.out.println("Failed to delete user list");
        }
    }
}
