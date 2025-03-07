package Bai66;
import java.util.Scanner;
public class bai6_6 {
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		
		System.out.print("Enter number of rows: ");
		int rows = keyboard.nextInt();
		System.out.print("Enter number of columns: ");
		int columns = keyboard.nextInt();
		
		int[][] matrix1 = new int[rows][columns];
        int[][] matrix2 = new int[rows][columns];
        int[][] sumMatrix = new int[rows][columns];
        
        System.out.println("Enter elements of Maxtrix 1: ");
        for(int i = 0; i < rows; i++) {
        	for(int j = 0; j < columns; j++) {
        		System.out.println("Element [" + i + "][" + j +"]: ");
        		matrix1[i][j] = keyboard.nextInt();
        	}
        }
        
        System.out.println("Enter elements of Maxtrix 2: ");
        for(int i = 0; i < rows; i++) {
        	for(int j = 0; j < columns; j++) {
        		System.out.println("Element [" + i + "][" + j +"]: ");
        		matrix2[i][j] = keyboard.nextInt();
        	}
        }
        
        for(int i = 0; i < rows; i++) {
        	for(int j = 0; j < columns; j++) {
        		System.out.print(sumMatrix[i][j] + " ");
        	}
        }
       
        System.out.println("Sum of the two matrices: ");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(sumMatrix[i][j] + " ");
            }
            System.out.println();
        }
	}
}
