package cc.sukazyo.sekai_scores;

import javax.annotation.Nonnull;

public record Song(
		@Nonnull String name,
		@Nonnull SongUnit unit,
		@Nonnull Difficulties difficulties
) {}
