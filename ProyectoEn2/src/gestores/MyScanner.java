package gestores;

import java.util.Scanner;

public class MyScanner {
	private static Scanner scanner = null ;
	static {
		scanner= new Scanner(System.in);
	}
	
	public static Scanner getScanner () {
		return scanner;
	}
	
	public static void closeScanner() {
		scanner.close();
	}
}
