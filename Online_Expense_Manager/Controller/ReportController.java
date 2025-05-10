package Controller;

import entity.Expense;
import entity.User;
import serviceimpl.ExpenseServiceImpl;
import serviceimpl.UserServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ReportController {

    private static final Scanner sc = new Scanner(System.in);
    private static final ExpenseServiceImpl expenseService = new ExpenseServiceImpl();
    private static final UserServiceImpl userService = new UserServiceImpl();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== 📊 REPORTS MENU =====");
            System.out.println("1. Total Expenses by Category");
            System.out.println("2. Total Expenses by User");
            System.out.println("3. Back to Dashboard");
            System.out.print("Enter your choice: ");

            int choice = getValidInt();

            switch (choice) {
                case 1:
                    reportByCategory();
                    break;
                case 2:
                    reportByUser();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("❌ Invalid choice. Try again.");
            }
        }
    }

    private static void reportByCategory() {
        Map<String, Double> result = expenseService.getExpenseByCategory();
        if (result.isEmpty()) {
            System.out.println("ℹ️ No data to display.");
            return;
        }

        System.out.println("\n===== Total Expenses by Category =====");
        result.forEach((cat, sum) ->
            System.out.printf("📂 %-15s : ₹ %.2f%n", cat, sum)
        );
    }

    private static void reportByUser() {
        // Build totals
        Map<String, Double> totals = new HashMap<>();
        List<Expense> all = expenseService.getAllExpenses();
        for (Expense e : all) {
            User u = e.getUser();
            String name = u != null ? u.getName() + " (ID:" + u.getUser_id() + ")" : "Unknown";
            totals.put(name, totals.getOrDefault(name, 0.0) + e.getAmount().doubleValue());
        }

        if (totals.isEmpty()) {
            System.out.println("ℹ️ No data to display.");
            return;
        }

        System.out.println("\n===== Total Expenses by User =====");
        totals.forEach((user, sum) ->
            System.out.printf("👤 %-25s : ₹ %.2f%n", user, sum)
        );
    }

    private static int getValidInt() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("❌ Invalid number. Try again: ");
            }
        }
    }
}
