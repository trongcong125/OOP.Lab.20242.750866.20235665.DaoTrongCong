package Bai64;
import java.util.Scanner;
public class bai6_4 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		String[] fullNames = { "January", "February", "March", "April", "May", "June",
				"July", "August", "September", "October", "November", "December"};
		String[] abbreviations = { "Jan.", "Feb.", "Mar.", "Apr.", "May", "June",
                "July", "Aug.", "Sept.", "Oct.", "Nov.", "Dec." };
        String[] threeLetters = { "Jan", "Feb", "Mar", "Apr", "May", "Jun",
                "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        int[] daysInCommonYear = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        int[] daysInLeapYear = { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        
        int monthIndex = -1;
        int year = -1;

        while (true) {
            System.out.print("Enter month: ");
            String monthInput = scanner.nextLine().trim();

            for (int i = 0; i < 12; i++) {
                if (monthInput.equalsIgnoreCase(fullNames[i]) ||
                    monthInput.equalsIgnoreCase(abbreviations[i]) ||
                    monthInput.equalsIgnoreCase(threeLetters[i]) ||
                    monthInput.equals(String.valueOf(i + 1))) {
                    monthIndex = i;
                    break;
                }
            }

            if (monthIndex != -1) break;
            System.out.println("Invalid month. Please try again.");
        }

        while (true) {
            System.out.print("Enter year: ");
            String yearInput = scanner.nextLine().trim();

            if (yearInput.matches("\\d+")) {
                year = Integer.parseInt(yearInput);
                if (year >= 0) break;
            }
            System.out.println("Invalid year. Please enter a non-negative integer.");
        }

        boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
        int days = isLeapYear ? daysInLeapYear[monthIndex] : daysInCommonYear[monthIndex];

        System.out.println(fullNames[monthIndex] + " " + year + " has " + days + " days.");
	}

}
