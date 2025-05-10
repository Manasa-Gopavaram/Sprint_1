package Controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import entity.Account;
import entity.User;
import service.AccountService;
import service.UserService;
import serviceimpl.AccountServiceImpl;
import serviceimpl.UserServiceImpl;

public class AccountController {

    private static final AccountService accountService = new AccountServiceImpl();
    private static final UserService userService      = new UserServiceImpl();
    private static final Scanner sc                   = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== Account Management Menu =====");
            System.out.println("1. Create Account");
            System.out.println("2. View All Accounts");
            System.out.println("3. Update Account");
            System.out.println("4. Delete Account");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: createAccount();    break;
                case 2: viewAllAccounts();  break;
                case 3: updateAccount();    break;
                case 4: deleteAccount();    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("❌ Invalid choice! Please try again.");
            }
        }
    }

    private static void createAccount() {
        System.out.print("Enter User ID: ");
        int userId = sc.nextInt();
        sc.nextLine();

        User user = userService.getUserById(String.valueOf(userId));
        if (user == null) {
            System.out.println("❌ No such user. Please create the user first.");
            return;
        }

        System.out.print("Enter Account Type (e.g., Savings/Current): ");
        String type = sc.nextLine();

        System.out.print("Enter Initial Balance: ");
        BigDecimal balance = getValidBalance();

        Account acct = new Account(user, type, balance);
        Account created = accountService.createAccount(acct);

        if (created != null) {
            System.out.println("✅ Account created! ID: " + created.getAccount_id());
            System.out.println("Account Type: " + created.getAccount_type() + " | Balance: " + created.getBalance());
        } else {
            System.out.println("❌ Failed to create account!");
        }
    }

    private static void viewAllAccounts() {
        List<Account> accounts = accountService.getAllAccounts();
        if (accounts == null || accounts.isEmpty()) {
            System.out.println("⚠ No accounts found.");
            return;
        }

        System.out.println("\n---- List of Accounts ----");
        System.out.printf("%-10s %-10s %-15s %-15s\n", "Acct ID", "User ID", "Type", "Balance");
        System.out.println("-----------------------------------------------");
        for (Account a : accounts) {
            System.out.printf("%-10d %-10d %-15s %-15.2f\n",
                a.getAccount_id(),
                a.getUser().getUser_id(),
                a.getAccount_type(),
                a.getBalance());
        }
    }

    private static void updateAccount() {
        System.out.print("Enter Account ID to update: ");
        int acctId = sc.nextInt();
        sc.nextLine();

        Account existing = accountService.getAccountById(String.valueOf(acctId));
        if (existing == null) {
            System.out.println("❌ Account not found!");
            return;
        }

        System.out.print("Enter new Account Type: ");
        String type = sc.nextLine();

        System.out.print("Enter new Balance: ");
        BigDecimal balance = getValidBalance();

        existing.setAccount_type(type);
        existing.setBalance(balance);

        Account updated = accountService.updateAccount(String.valueOf(acctId), existing);
        if (updated != null) {
            System.out.println("✅ Account updated successfully!");
        } else {
            System.out.println("❌ Failed to update account!");
        }
    }

    private static void deleteAccount() {
        System.out.print("Enter Account ID to delete: ");
        int acctId = sc.nextInt();
        sc.nextLine();

        Account toDel = accountService.getAccountById(String.valueOf(acctId));
        if (toDel == null) {
            System.out.println("❌ Account not found!");
            return;
        }

        System.out.print("Are you sure? (Y/N): ");
        String confirm = sc.nextLine();
        if (confirm.equalsIgnoreCase("Y")) {
            String res = accountService.deleteAccount(String.valueOf(acctId));
            System.out.println(res.equals("Account deleted successfully")
                ? "✅ Account deleted!"
                : "❌ " + res);
        } else {
            System.out.println("⚠ Deletion cancelled.");
        }
    }

    private static BigDecimal getValidBalance() {
        BigDecimal balance = BigDecimal.ZERO;
        boolean valid = false;

        while (!valid) {
            try {
                balance = BigDecimal.valueOf(sc.nextDouble());
                sc.nextLine();
                if (balance.compareTo(BigDecimal.ZERO) >= 0) {
                    valid = true;
                } else {
                    System.out.println("❌ Balance cannot be negative. Please enter a valid balance.");
                }
            } catch (Exception e) {
                System.out.println("❌ Invalid input! Please enter a valid balance.");
                sc.nextLine(); // Clear buffer
            }
        }
        return balance;
    }
}
