package cc.sukazyo.sekai_cli.client;

import cc.sukazyo.sekai_cli.ClientMain;
import cc.sukazyo.sekai_cli.Config;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static cc.sukazyo.sekai_cli.Log._debug;
import static cc.sukazyo.sekai_cli.Log._user;

public class Configs {
	
	public static void main (String[] args) {
		
		final List<String> $args = new java.util.ArrayList<>(List.of(args));
		Path configRoot = Config.getSysUserConfigRoot();
		if ($args.remove("--user"))
			configRoot = Config.getSysUserConfigRoot();
		if ($args.remove("--sys"))
			configRoot = Config.getSysGlobalConfigRoot();
		
		if ($args.isEmpty()) {
			showConfigPath(configRoot);
		} else if ($args.get(0).equals("create")) {
			createConfigFile(configRoot);
		} else if ($args.get(0).equals("list") || $args.get(0).equals("show")) {
			listConfig();
		} else {
			System.out.println("unknown command in config manager.");
		}
		
	}
	
	private static void listConfig () {
		ClientMain.config().echo();
	}
	
	private static void createConfigFile (Path configRoot) {
		// check system/user config root
		if (!configRoot.toFile().isDirectory()) {
			_user(String.format("the config root `%s` is not a directory!", configRoot.toAbsolutePath()));
			return;
		}
		// check and create sekai config dir
		final Path configPath = Config.getAppConfigPath(configRoot);
		final File configPathDir = configPath.toFile();
		if (configPathDir.exists()) {
			if (!configPathDir.isDirectory()) {
				_user(String.format("the config path `%s` is not a directory!", configPath.toAbsolutePath()));
				return;
			}
		} else {
			try {
				_debug("try create config path " + configPath.toAbsolutePath());
				if (!configPathDir.mkdir()) {
					_user(String.format("failed to create config path `%s`", configPath.toAbsolutePath()));
					return;
				}
			} catch (SecurityException e) {
				_user(String.format("%s: permission denied", configPath.toAbsolutePath()));
				return;
			}
		}
		// create config file
		final File configFile = Config.getAppConfigFile(configPath);
		try {
			_debug("try create config " + configFile.getAbsolutePath());
			if (!configFile.createNewFile()) {
				_user(String.format("%s: already exists.", configFile.getAbsolutePath()));
				return;
			}
		} catch (IOException e) {
			_user(String.format("%s: create file failed: %s", configFile.getAbsolutePath(), e.getMessage()));
			return;
		} catch (SecurityException e) {
			_user(String.format("%s: permission denied", configFile.getAbsolutePath()));
			return;
		}
		System.out.println(configFile.getAbsolutePath());
	}
	
	private static void showConfigPath (Path configRoot) {
		System.out.println(Config.getAppConfigFile(Config.getAppConfigPath(configRoot)).getAbsolutePath());
	}
	
}
