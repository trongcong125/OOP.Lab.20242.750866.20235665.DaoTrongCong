package Bai225;
import java.util.Scanner;

import javax.swing.JOptionPane;
public class bai2_25 {
	public static void main(String[] args) {
        try {
            String strNum1 = JOptionPane.showInputDialog("Nhập số thứ nhất:");
            double num1 = Double.parseDouble(strNum1);

            String strNum2 = JOptionPane.showInputDialog("Nhập số thứ hai:");
            double num2 = Double.parseDouble(strNum2);

            double sum = num1 + num2;
            double difference = num1 - num2;
            double product = num1 * num2;
            String result;

            if (num2 != 0) {
                double quotient = num1 / num2;
                result = "Tổng: " + sum + "\n"
                       + "Hiệu: " + difference + "\n"
                       + "Tích: " + product + "\n"
                       + "Thương: " + quotient;
            } else {
                result = "Tổng: " + sum + "\n"
                       + "Hiệu: " + difference + "\n"
                       + "Tích: " + product + "\n"
                       + "Thương: Không thể chia cho 0";
            }

            JOptionPane.showMessageDialog(null, result, "Kết quả", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}
