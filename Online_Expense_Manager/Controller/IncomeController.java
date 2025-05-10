package Controller;

import entity.Account;
import entity.Income;
import service.AccountService;
import service.IncomeService;
import serviceimpl.AccountServiceImpl;
import serviceimpl.IncomeServiceImpl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class IncomeController {

    private static final IncomeService incomeService = new IncomeServiceImpl();
    private static final AccountService accountService = new AccountServiceImpl();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== Income Management Menu =====");
            System.out.println("1. Add Income");
            System.out.println("2. View All Incomes");
            System.out.println("3. Update Income");
            System.out.println("4. Delete Income");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1: addIncome(); break;
                case 2: viewAllIncomes(); break;
                case 3: updateIncome(); break;
                case 4: deleteIncome(); break;
                case 5:
                    System.out.println("Exiting Income Management...");
                    return;
                default:
                    System.out.println("❌ Invalid choice. Try again.");
            }
        }
    }

    private static void addIncome() {
        System.out.print("Enter Account ID: ");
        int accountId = sc.nextInt();
        sc.nextLine();

        Account account = accountService.getAccountById(String.valueOf(accountId));
        if (account == null) {
            System.out.println("❌ No such account. Please create an account first.");
            return;
        }

        System.out.print("Enter Income Amount: ");
        BigDecimal amount = getValidAmount();

        System.out.print("Enter Income Source: ");
        String source = sc.nextLine();

        System.out.print("Enter Income Description: ");
        String description = sc.nextLine();

        Date currentDate = new Date(); // Today's date

        Income income = new Income(account, amount, source, currentDate, description);

        Income created = incomeService.createIncome(income);
        if (created != null) {
            System.out.println("✅ Income added successfully!");
        } else {
            System.out.println("❌ Failed to add income!");
        }
    }

    private static void viewAllIncomes() {
        List<Income> incomes = incomeService.getAllIncomes();
        if (incomes == null || incomes.isEmpty()) {
            System.out.println("⚠ No incomes found.");
            return;
        }

        System.out.println("\n---- List of Incomes ----");
        System.out.printf("%-10s %-10s %-10s %-20s %-15s %-20s\n", "Income ID", "Account ID", "Amount", "Source", "Date", "Description");
        System.out.println("--------------------------------------------------------------------------------------------");
        for (Income income : incomes) {
            System.out.printf("%-10d %-10d %-10.2f %-20s %-15tF %-20s\n",
                    income.getIncome_id(),
                    income.getAccount().getAccount_id(),
                    income.getAmount(),
                    income.getSource(),
                    income.getDate(),
                    income.getDescription());
        }
    }

    private static void updateIncome() {
        System.out.print("Enter Income ID to update: ");
        int incomeId = sc.nextInt();
        sc.nextLine();

        Income existingIncome = incomeService.getIncomeById(String.valueOf(incomeId));
        if (existingIncome == null) {
            System.out.println("❌ Income not found!");
            return;
        }

        System.out.print("Enter New Income Amount: ");
        BigDecimal amount = getValidAmount();

        System.out.print("Enter New Source: ");
        String source = sc.nextLine();

        System.out.print("Enter New Description: ");
        String description = sc.nextLine();

        existingIncome.setAmount(amount);
        existingIncome.setSource(source);
        existingIncome.setDescription(description);

        Income updated = incomeService.updateIncome(String.valueOf(incomeId), existingIncome);
        if (updated != null) {
            System.out.println("✅ Income updated successfully!");
        } else {
            System.out.println("❌ Failed to update income!");
        }
    }

    private static void deleteIncome() {
        System.out.print("Enter Income ID to delete: ");
        int incomeId = sc.nextInt();
        sc.nextLine();

        Income toDelete = incomeService.getIncomeById(String.valueOf(incomeId));
        if (toDelete == null) {
            System.out.println("❌ Income not found!");
            return;
        }

        System.out.print("Are you sure you want to delete? (Y/N): ");
        String confirm = sc.nextLine();

        if (confirm.equalsIgnoreCase("Y")) {
            String result = incomeService.deleteIncome(String.valueOf(incomeId));
            System.out.println(result.equals("Income deleted successfully") ? "✅ Income deleted!" : "❌ " + result);
        } else {
            System.out.println("⚠ Deletion cancelled.");
        }
    }

    private static BigDecimal getValidAmount() {
        BigDecimal amount = BigDecimal.ZERO;
        boolean valid = false;

        while (!valid) {
            try {
                amount = BigDecimal.valueOf(sc.nextDouble());
                sc.nextLine();
                if (amount.compareTo(BigDecimal.ZERO) >= 0) {
                    valid = true;
                } else {
                    System.out.println("❌ Amount cannot be negative. Try again.");
                }
            } catch (Exception e) {
                System.out.println("❌ Invalid amount! Try again.");
                sc.nextLine(); // clear invalid input
            }
        }
        return amount;
    }
}
