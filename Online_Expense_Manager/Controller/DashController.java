package Controller;

import java.util.Scanner;

public class DashController {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== 💼 Expense Tracker Dashboard =====");
            System.out.println("1. Manage Users");
            System.out.println("2. Manage Accounts");
            System.out.println("3. Manage Expenses");
            System.out.println("4. Manage Incomes");
            System.out.println("5. Manage Loans");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = getValidInt();

            switch (choice) {
                case 1:
                    UserController.main(null);
                    break;
                case 2: 
                    AccountController.main(null);
                    break;
                case 3:
                    ExpenseController.main(null);
                    break;
                case 4:
                    IncomeController.main(null);
                    break;
                case 5:
                    LoanController.main(null);
                    break;
                case 6:
                    System.out.println("👋 Exiting Expense Tracker. Goodbye!");
                    return;
                default:
                    System.out.println("❌ Invalid choice. Try again.");
            }
        }
    }

    private static int getValidInt() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.print("❌ Invalid number. Try again: ");
            }
        }
    }
}
