package cc.sukazyo.sekai_scores;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;

public record UserScore (
		@Nonnull Song song,
		@Nonnull Difficulty difficulty,
		@Nonnull ScoreBase score,
		@Nonnull LocalDateTime playtime
) {}
