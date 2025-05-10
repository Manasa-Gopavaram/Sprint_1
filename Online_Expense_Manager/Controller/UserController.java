package Controller;

import java.util.List;
import java.util.Scanner;

import service.UserService;
import serviceimpl.UserServiceImpl;
import entity.User;

public class UserController {

    private static final UserService userService = new UserServiceImpl();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== User Management Menu =====");
            System.out.println("1. Create User");
            System.out.println("2. View All Users");
            System.out.println("3. Update User");
            System.out.println("4. Delete User");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    createUser();
                    break;
                case 2:
                    viewAllUsers();
                    break;
                case 3:
                    updateUser();
                    break;
                case 4:
                    deleteUser();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("❌ Invalid choice! Please try again.");
            }
        }
    }

    // Create a new User (No need to enter user_id since it is auto-generated)
    private static void createUser() {
        System.out.print("Enter Name: ");
        String name = sc.nextLine();  // Take Name input
        
        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        // Now use 'name'
        User user = new User(name, email, password);

        User created = userService.createUser(user);

        if (created != null) {
            System.out.println("✅ User created successfully!");
            System.out.println("Generated User ID: " + created.getUser_id()); // Show generated user_id
        } else {
            System.out.println("❌ Failed to create user!");
        }
    }

    // View all Users
    private static void viewAllUsers() {
        List<User> users = userService.getAllUsers();

        if (users == null || users.isEmpty()) {
            System.out.println("⚠ No users found.");
            return;
        }

        System.out.println("\n---- List of Users ----");
        System.out.printf("%-10s %-20s %-25s %-25s\n", "User ID", "Name", "Email", "Password");
        System.out.println("---------------------------------------------------------------");

        for (User user : users) {
            // Displaying the user_id, name, email, and password
            System.out.printf("%-10s %-20s %-25s %-25s\n",
                    user.getUser_id(),  // Displaying the user ID
                    user.getName(),
                    user.getEmail(),
                    user.getPassword());
        }
    }

    // Update a User
    private static void updateUser() {
        System.out.print("Enter User_ID to update: ");
        String userID = sc.nextLine(); // no need for conversion

        User existingUser = userService.getUserById(userID);  // get by email

        if (existingUser == null) {
            System.out.println("❌ User not found!");
            return;
        }

        System.out.print("Enter new Name: ");
        String name = sc.nextLine();
        
        System.out.print("Enter new Email: ");
        String email = sc.nextLine();

        System.out.print("Enter new Password: ");
        String password = sc.nextLine();

        existingUser.setName(name);
        existingUser.setEmail(email);
        existingUser.setPassword(password);

        User updatedUser = userService.updateUser(userID, existingUser);

        if (updatedUser != null) {
            System.out.println("✅ User updated successfully!");
        } else {
            System.out.println("❌ Failed to update user!");
        }
    }

    // Delete a User
    private static void deleteUser() {
        System.out.print("Enter User_ID to delete: ");
        String userID = sc.nextLine();  // use email (String)

        User userToDelete = userService.getUserById(userID);  // get by email

        if (userToDelete == null) {
            System.out.println("❌ User not found!");
            return;
        }

        System.out.print("Are you sure you want to delete this user? (Y/N): ");
        String confirmation = sc.nextLine();

        if (confirmation.equalsIgnoreCase("Y")) {
            String response = userService.deleteUser(userID);  // pass email (String)
            if (response.equals("User deleted successfully")) {
                System.out.println("✅ User deleted successfully!");
            } else {
                System.out.println("❌ Failed to delete user!");
            }
        } else {
            System.out.println("⚠ Deletion cancelled.");
        }
    }
}
