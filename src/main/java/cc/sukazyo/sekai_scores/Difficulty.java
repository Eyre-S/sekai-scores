package cc.sukazyo.sekai_scores;

import javax.annotation.Nonnegative;

public record Difficulty(
		@Nonnegative int level,
		@Nonnegative int noteCount
) {}
