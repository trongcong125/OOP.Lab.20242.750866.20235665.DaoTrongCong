package Bai225;
import javax.swing.JOptionPane;
public class bai2_25 {
	public static void main(String[] args) {
        String strNum1, strNum2;
        strNum1 = JOptionPane.showInputDialog(null, 
        "Please input the first number", "Input the first number",
         JOptionPane.INFORMATION_MESSAGE);

        strNum2 = JOptionPane.showInputDialog(null, 
         "Please input the second number", "Input the second number",
          JOptionPane.INFORMATION_MESSAGE);

        double num1 = Double.parseDouble(strNum1);
        double num2 = Double.parseDouble(strNum2);
        double sum = num1+num2;

        JOptionPane.showMessageDialog(null, "The sum is: " + sum,
         "Result", JOptionPane.INFORMATION_MESSAGE);
        
        System.exit(0);
    }
}
