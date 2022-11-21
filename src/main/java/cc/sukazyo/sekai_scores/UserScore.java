package cc.sukazyo.sekai_scores;

import javax.annotation.Nonnull;
import java.time.ZonedDateTime;

public record UserScore (
		@Nonnull Song song,
		@Nonnull Difficulty difficulty,
		@Nonnull ScoreBase score,
		@Nonnull ZonedDateTime playtime
) {
	
	@Override
	@Nonnull
	public String toString () {
		return String.format(
				"{\"song\":%s,\"difficulty\":%s,\"score\":%s,\"playtime\":%d}",
				song, difficulty, score,
				playtime.toInstant().toEpochMilli()
		);
	}
	
}
