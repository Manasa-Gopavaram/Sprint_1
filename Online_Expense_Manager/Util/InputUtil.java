package util;


import java.math.BigDecimal;
import java.util.Scanner;

public class InputUtil {
    private static final Scanner scanner = new Scanner(System.in);

    public static int getValidInt() {
        while (true) {
            try {
                String input = scanner.nextLine();
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                System.out.print("❌ Invalid input. Please enter a valid integer: ");
            }
        }
    }

    public static BigDecimal getValidBigDecimal() {
        while (true) {
            try {
                String input = scanner.nextLine();
                return new BigDecimal(input.trim());
            } catch (NumberFormatException e) {
                System.out.print("❌ Invalid input. Please enter a valid decimal number: ");
            }
        }
    }
}
