package Bai63;
import java.util.Scanner;
public class bai6_3 {
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Chieu cao hinh tam giac (n): ");
        int n = scanner.nextInt();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n - i; j++) {
                System.out.print(" ");
            }
            for (int k = 1; k <= (2 * i - 1); k++) {
                System.out.print("*");
            }
            System.out.println();
        }
	}
}
