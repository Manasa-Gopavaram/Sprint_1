package Controller;

import entity.Expense;
import entity.User;
import entity.Account;
import service.ExpenseService;
import service.UserService;
import service.AccountService;
import serviceimpl.ExpenseServiceImpl;
import serviceimpl.UserServiceImpl;
import serviceimpl.AccountServiceImpl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ExpenseController {

    private ExpenseService expenseService;
    private UserService userService;
    private AccountService accountService;
    private Scanner sc;

    public ExpenseController() {
        this.expenseService = new ExpenseServiceImpl();
        this.userService = new UserServiceImpl();
        this.accountService = new AccountServiceImpl();
        this.sc = new Scanner(System.in);
    }

    public static void main(String[] args) {
        ExpenseController controller = new ExpenseController();
        controller.expenseMenu();
    }

    public void expenseMenu() {
        while (true) {
            System.out.println("\n===== Expense Management Menu =====");
            System.out.println("1. Add Expense");
            System.out.println("2. View All Expenses");
            System.out.println("3. Update Expense");
            System.out.println("4. Delete Expense");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = getValidInt();

            switch (choice) {
                case 1: addExpense(); break;
                case 2: viewAllExpenses(); break;
                case 3: updateExpense(); break;
                case 4: deleteExpense(); break;
                case 5:
                    System.out.println("Exiting Expense Management...");
                    return;
                default:
                    System.out.println("❌ Invalid choice. Please try again.");
            }
        }
    }

    private void addExpense() {
        try {
            System.out.print("Enter User ID: ");
            int userId = getValidInt();
            User user = userService.getUserById(String.valueOf(userId));
            if (user == null) {
                System.out.println("❌ User not found!");
                return;
            }

            System.out.print("Enter Account ID: ");
            int accountId = getValidInt();
            Account account = accountService.getAccountById(String.valueOf(accountId));
            if (account == null) {
                System.out.println("❌ Account not found!");
                return;
            }

            System.out.print("Enter Expense Amount: ");
            BigDecimal amount = getValidBigDecimal();

            System.out.print("Enter Expense Category: ");
            String category = sc.nextLine();

            System.out.print("Enter Expense Date (YYYY-MM-DD): ");
            Date date = parseDate(sc.nextLine());
            if (date == null) return;

            System.out.print("Enter Expense Description: ");
            String description = sc.nextLine();

            // Create Expense object with the user, account, and other details
            Expense expense = new Expense(user, account, amount, category, date, description);
            Expense createdExpense = expenseService.createExpense(expense);

            if (createdExpense != null) {
                System.out.println("✅ Expense added successfully!");
            } else {
                System.out.println("❌ Failed to add expense!");
            }

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    private void viewAllExpenses() {
        try {
            List<Expense> expenses = expenseService.getAllExpenses();
            if (expenses == null || expenses.isEmpty()) {
                System.out.println("ℹ️ No expenses found.");
            } else {
                System.out.printf("%-10s %-10s %-10s %-15s %-15s %-20s\n",
                        "ID", "User ID", "Amount", "Category", "Date", "Description");
                System.out.println("---------------------------------------------------------------------------------------");
                for (Expense expense : expenses) {
                    System.out.printf("%-10d %-10d %-10.2f %-15s %-15tF %-20s\n",
                            expense.getExpense_id(),
                            expense.getUser().getUser_id(),
                            expense.getAmount(),
                            expense.getCategory(),
                            expense.getDate(),
                            expense.getDescription());
                }
            }
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void updateExpense() {
        try {
            System.out.print("Enter Expense ID to update: ");
            int expenseId = getValidInt();
            Expense expense = expenseService.getExpenseById(String.valueOf(expenseId));
            if (expense == null) {
                System.out.println("❌ Expense not found!");
                return;
            }

            System.out.print("Enter New Amount: ");
            BigDecimal amount = getValidBigDecimal();
            expense.setAmount(amount);

            System.out.print("Enter New Category: ");
            String category = sc.nextLine();
            expense.setCategory(category);

            System.out.print("Enter New Description: ");
            String description = sc.nextLine();
            expense.setDescription(description);

            expenseService.updateExpense(String.valueOf(expenseId), expense);
            System.out.println("✅ Expense updated successfully!");

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void deleteExpense() {
        try {
            System.out.print("Enter Expense ID to delete: ");
            int expenseId = getValidInt();

            Expense toDelete = expenseService.getExpenseById(String.valueOf(expenseId));
            if (toDelete == null) {
                System.out.println("❌ Expense not found!");
                return;
            }

            System.out.print("Are you sure you want to delete? (Y/N): ");
            String confirm = sc.nextLine();
            if (!confirm.equalsIgnoreCase("Y")) {
                System.out.println("⚠ Deletion cancelled.");
                return;
            }

            String result = expenseService.deleteExpense(String.valueOf(expenseId));
            System.out.println("✅ " + result);

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    // Utility Methods
    private int getValidInt() {
        while (!sc.hasNextInt()) {
            System.out.print("❌ Invalid input. Please enter a number: ");
            sc.next();
        }
        int num = sc.nextInt();
        sc.nextLine(); // consume leftover newline
        return num;
    }

    private BigDecimal getValidBigDecimal() {
        while (!sc.hasNextBigDecimal()) {
            System.out.print("❌ Invalid input. Please enter a valid amount: ");
            sc.next();
        }
        BigDecimal value = sc.nextBigDecimal();
        sc.nextLine(); // consume leftover newline
        return value;
    }

    private Date parseDate(String input) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(input);
        } catch (Exception e) {
            System.out.println("❌ Invalid date format. Use YYYY-MM-DD.");
            return null;
        }
    }
}
