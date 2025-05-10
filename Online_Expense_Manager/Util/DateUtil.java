package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static Date parseDate(String input) {
        try {
            return dateFormat.parse(input.trim());
        } catch (ParseException e) {
            System.out.println("❌ Invalid date format. Please use YYYY-MM-DD.");
            return null;
        }
    }
}
