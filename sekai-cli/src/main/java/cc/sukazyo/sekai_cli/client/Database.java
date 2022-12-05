package cc.sukazyo.sekai_cli.client;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static cc.sukazyo.sekai_cli.Log._debug;
import static cc.sukazyo.sekai_cli.Log._user;

public class Database {
	
	public static void main (String[] args) {
		
		final List<String> $args = Arrays.asList(args);
		
		final String subcommand = $args.remove(0);
		if (subcommand.equals("import")) {
			if ($args.isEmpty()) {
				System.out.println("""
						database import: <type> <file-path>
						currently available types:
						  song
						  song-difficulty
						""");
			} else {
				final Path file;
				try { file = Paths.get($args.remove(1)); }
				catch (IndexOutOfBoundsException e) {
					_user("database import: need a file path.");
					_debug(e);
				}
				switch ($args.remove(0)) {
					case "song" ->
							_user("type song: WIP");
					case "song-difficulty" ->
							_user("type song-difficulty: WIP");
					default ->
							_user("unavailable type.\ncurrently available types:\n  song\n  song-difficulty");
				}
			}
		}
		
	}
	
}
