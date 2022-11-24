package cc.sukazyo.sekai_cli.client;

import cc.sukazyo.sekai_scores.ScoreBase;

public class PrintScore {
	
	public static final String MESSAGE_AP = "ALL PERFECT!";
	public static final String MESSAGE_FC = "FULL COMBO!";
	
	public static String getMessageQuote(String message) {
		return "\\ " + message + " /";
	}
	
	public static String printTableScoreBase (ScoreBase score) {
		return String.format("""
				  COMBO          %16s
				╭────────────────────┬─────────────────┬───────────────╮
				│                    │ PERFECT    %4d │               │
				│                    │ GREAT      %4d │ LATE     FAST │
				│        %-4d        │ GOOD       %4d │ [%-4d   %4d] │
				│                    │ BAD        %4d │ FLICK    %4d │
				│                    │ MISS       %4d │               │
				╰────────────────────┴─────────────────┴───────────────╯
				""",
				score.noteCount() == score.combo() ? getMessageQuote((score.noteCount()==score.perfect()?MESSAGE_AP : MESSAGE_FC)) : "",
				score.perfect(),
				score.great(),
				score.combo(), score.good(), score.slow(), score.fast(),
				score.bad(), score.flick(),
				score.miss()
		);
	}
	
}
