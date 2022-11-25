package cc.sukazyo.sekai_cli;

import java.util.Scanner;

public class CLI {
	
	public static void main (String[] args) {
		
		final Scanner in = new Scanner(System.in);
		System.out.print("$ java -jar sekai-cli.jar ");
		String[] $args = in.nextLine().split(" ");
		ClientMain.main($args);
		
	}
	
}
