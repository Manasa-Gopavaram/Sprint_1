package Controller;

import entity.Loan;
import entity.Account;
import service.LoanService;
import serviceimpl.LoanServiceImpl;
import serviceimpl.AccountServiceImpl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LoanController {

    private static final LoanService loanService = new LoanServiceImpl();
    private static final AccountServiceImpl accountService = new AccountServiceImpl();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== Loan Management Menu =====");
            System.out.println("1. Add Loan");
            System.out.println("2. View All Loans");
            System.out.println("3. Update Loan");
            System.out.println("4. Delete Loan");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addLoan();
                    break;
                case 2:
                    viewAllLoans();
                    break;
                case 3:
                    updateLoan();
                    break;
                case 4:
                    deleteLoan();
                    break;
                case 5:
                    System.out.println("Exiting Loan Management...");
                    return;
                default:
                    System.out.println("❌ Invalid choice! Please try again.");
            }
        }
    }

    // Add a new Loan
    private static void addLoan() {
        System.out.print("Enter Account ID: ");
        int accountId = sc.nextInt();
        sc.nextLine(); // consume newline

        Account account = accountService.getAccountById(String.valueOf(accountId));
        if (account == null) {
            System.out.println("❌ Account not found!");
            return;
        }

        System.out.print("Enter Loan Amount: ");
        BigDecimal loanAmount = sc.nextBigDecimal();
        sc.nextLine(); // consume newline

        System.out.print("Enter Lender Name: ");
        String lenderName = sc.nextLine();

        System.out.print("Enter Loan Start Date (YYYY-MM-DD): ");
        Date startDate = parseDate(sc.nextLine());
        if (startDate == null) return;

        System.out.print("Enter Loan End Date (YYYY-MM-DD): ");
        Date endDate = parseDate(sc.nextLine());
        if (endDate == null) return;

        Loan loan = new Loan(account, lenderName, loanAmount, startDate, endDate);

        // Check if loan creation is successful
        Loan createdLoan = loanService.createLoan(loan);

        if (createdLoan != null) {
            System.out.println("✅ Loan added successfully!");
        } else {
            System.out.println("❌ Failed to add loan! Check the database or validation.");
        }
    }

    // View all Loans
    private static void viewAllLoans() {
        List<Loan> loans = loanService.getAllLoans();

        if (loans == null || loans.isEmpty()) {
            System.out.println("⚠ No loans found.");
            return;
        }

        System.out.println("\n---- List of Loans ----");
        System.out.printf("%-10s %-15s %-15s %-20s %-15s %-15s\n", "Loan ID", "Account ID", "Lender Name", "Loan Amount", "Start Date", "End Date");
        System.out.println("----------------------------------------------------------------------------");

        for (Loan loan : loans) {
            System.out.printf("%-10d %-15d %-15s %-20s %-15s %-15s\n",
                    loan.getLoan_id(),
                    loan.getAccount().getAccount_id(),
                    loan.getLender_name(),
                    loan.getLoan_amount(),
                    loan.getStart_date(),
                    loan.getEnd_date());
        }
    }

    // Update a Loan
    private static void updateLoan() {
        System.out.print("Enter Loan ID to update: ");
        int loanId = sc.nextInt();
        sc.nextLine(); // consume newline

        Loan existingLoan = loanService.getLoanById(String.valueOf(loanId));
        if (existingLoan == null) {
            System.out.println("❌ Loan not found!");
            return;
        }

        System.out.print("Enter new Lender Name: ");
        String lenderName = sc.nextLine();
        existingLoan.setLender_name(lenderName);

        System.out.print("Enter new Loan Amount: ");
        BigDecimal loanAmount = sc.nextBigDecimal();
        sc.nextLine(); // consume newline
        existingLoan.setLoan_amount(loanAmount);

        System.out.print("Enter new Loan Start Date (YYYY-MM-DD): ");
        Date startDate = parseDate(sc.nextLine());
        if (startDate == null) return;
        existingLoan.setStart_date(startDate);

        System.out.print("Enter new Loan End Date (YYYY-MM-DD): ");
        Date endDate = parseDate(sc.nextLine());
        if (endDate == null) return;
        existingLoan.setEnd_date(endDate);

        Loan updatedLoan = loanService.updatedLoan(existingLoan);

        if (updatedLoan != null) {
            System.out.println("✅ Loan updated successfully!");
        } else {
            System.out.println("❌ Failed to update loan!");
        }
    }

    // Delete a Loan
    private static void deleteLoan() {
        System.out.print("Enter Loan ID to delete: ");
        int loanId = sc.nextInt();
        sc.nextLine(); // consume newline

        Loan loanToDelete = loanService.getLoanById(String.valueOf(loanId));

        if (loanToDelete == null) {
            System.out.println("❌ Loan not found!");
            return;
        }

        System.out.print("Are you sure you want to delete this loan? (Y/N): ");
        String confirmation = sc.nextLine();

        if (confirmation.equalsIgnoreCase("Y")) {
            String response = loanService.deleteLoan(String.valueOf(loanId));
            if (response.equals("Loan deleted successfully")) {
                System.out.println("✅ Loan deleted successfully!");
            } else {
                System.out.println("❌ Failed to delete loan!");
            }
        } else {
            System.out.println("⚠ Deletion cancelled.");
        }
    }

    // ----------------- Helper Methods -------------------

    private static Date parseDate(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false); // strict parsing
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            System.out.println("❌ Invalid date format. Please use YYYY-MM-DD.");
            return null;
        }
    }
}
