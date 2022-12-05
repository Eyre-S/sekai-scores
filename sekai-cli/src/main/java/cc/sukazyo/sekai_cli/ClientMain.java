package cc.sukazyo.sekai_cli;

import cc.sukazyo.sekai_cli.client.AddScore;
import cc.sukazyo.sekai_cli.client.Configs;
import cc.sukazyo.sekai_cli.client.Database;

import java.util.List;

import static cc.sukazyo.sekai_cli.Log._user;

public class ClientMain {
	
	private static Config config = null;
	
	public static Config config() {
		if (config == null) config = Config.load();
		if (config == null) {
			_user("read config failed.");
			System.exit(1);
		}
		return config;
	}
	
	public static void main (String[] args) {
		
		final List<String> $args = new java.util.ArrayList<>(List.of(args));
		if ($args.remove("--debug")) Log.enableDebugging();
		if ($args.remove("--verbose")) Log.enableDebugging();
		
		if ($args.size() > 0) {
			final String i0 = $args.remove(0);
			switch (i0) {
				case "add" -> {
					AddScore.main($args.toArray(String[]::new));
					$done();
				}
				case "config" -> {
					Configs.main($args.toArray(String[]::new));
					$done();
				}
				case "database" -> {
					Database.main($args.toArray(String[]::new));
					$done();
				}
			}
		}
		
		System.out.println("Unknown function call.\n  please check your program param.");
		
	}
	
	private static void $done () {
		System.exit(0);
	}
	
}
