package Bai223;
import javax.swing.JOptionPane;
public class bai2_23 {
	public static void main(String[] args) {
		String result;
		result = JOptionPane.showInputDialog("Please enter your name:");
		JOptionPane.showMessageDialog(null, "Hi "+ result + "!");
		System.exit(0);
	}

}
