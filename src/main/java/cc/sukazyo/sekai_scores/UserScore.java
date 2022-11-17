package cc.sukazyo.sekai_scores;

import javax.annotation.Nonnull;

public record UserScore (
		@Nonnull Song song,
		@Nonnull Difficulty difficulty,
		@Nonnull ScoreBase score
) {}
