package cc.sukazyo.sekai_cli;

import cc.sukazyo.sekai_cli.client.AddScore;

import java.util.Arrays;

public class ClientMain {
	
	public static final Config config = Config.loadUserConfig();
	
	public static void main (String[] args) {
		
		if (args.length > 0) {
			if (args[0].equals("add")) {
				AddScore.main(Arrays.copyOfRange(args, 1, args.length));
				$done();
			}
		}
		
		System.out.println("Unknown function call.\n  please check your program param.");
		
	}
	
	private static void $done () {
		System.exit(0);
	}
	
}
