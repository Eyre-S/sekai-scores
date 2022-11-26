package cc.sukazyo.sekai_cli.client;

import cc.sukazyo.sekai_scores.ScoreBase;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static cc.sukazyo.sekai_cli.Log._debug;
import static cc.sukazyo.sekai_cli.Log._user;

public class AddScore {
	
	public static void main (String[] args) {
		
		// process params available in add command
		Long user_sekai_id = null;
		final List<String> $args = new ArrayList<>(List.of(args));
		int $arg_i;
		if (($arg_i = $args.indexOf("--user")) != -1) {
			// subcommand --user to use a user instead of the user.id defined in Config
			$args.remove($arg_i);
			try {
				user_sekai_id = Long.parseLong($args.get($arg_i));
				$args.remove($arg_i);
				_debug("add: defined user id to use by param: " + user_sekai_id);
			}
			catch (IndexOutOfBoundsException e) {
				_user("add: param --user need a number as user_id.\n  use `--user <user_id>` to make it work properly.");
				_debug(e);
			} catch (NumberFormatException e) {
				_user("add: param --user need a user_id which type should be a number:\n  " + e.getMessage());
				_debug(e);
			}
		}
		args = $args.toArray(String[]::new);
		
		try {
			
			///--
			// scores input
			
			final int[] scores = new int[9];
			
			if (args.length == 0) {
				System.out.println("Input your score,");
				System.out.println("  score formatting: perfect ... miss late fast flick combo");
				System.out.print("> ");
				Scanner input = new Scanner(System.in);
				for (int i = 0; i < 9; i++) {
					scores[i] = input.nextInt();
				}
			} else {
				for (int i = 0; i < 9; i++) {
					scores[i] = Integer.parseInt(args[i]);
				}
			}
			
			for (int i : scores) {
				if (i < 0 || i > 9999) throw new InputMismatchException(String.format("input %d out of range of [0, 9999]", i));
			}
			
			ScoreBase scoreBase = new ScoreBase(
					scores[0], scores[1], scores[2], scores[3], scores[4],
					scores[6], scores[5], scores[7], scores[8]
			);
			
			///--
			// echo and making confirm
			
			
			System.out.println(PrintScore.printTableScoreBase(scoreBase));
			
		} catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
			System.out.println("unsupported yet.");
			_debug(e);
		} catch (InputMismatchException e) {
			System.out.println("unavailable input: " + e.getMessage());
			_debug(e);
		}
		
	}
	
}
