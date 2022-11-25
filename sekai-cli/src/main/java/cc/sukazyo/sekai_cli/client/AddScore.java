package cc.sukazyo.sekai_cli.client;

import cc.sukazyo.sekai_scores.ScoreBase;

import java.util.InputMismatchException;
import java.util.Scanner;

import static cc.sukazyo.sekai_cli.Log._debug;

public class AddScore {
	
	public static void main (String[] args) {
		
		try {
			
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
