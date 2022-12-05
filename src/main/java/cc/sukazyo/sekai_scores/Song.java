package cc.sukazyo.sekai_scores;

import cc.sukazyo.sekai_scores.util.Converter;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.Duration;
import java.time.ZonedDateTime;

public record Song(
		int id,
		@Nonnull String name,
		@Nonnull SongUnit unit,
		@Nonnull Difficulties difficulties,
		@Nullable String producer,
		@Nullable String arranger,
		@Nullable String composer,
		@Nullable String lyricist,
		@Nullable int[] bpm,
		@Nullable Duration duration,
		@Nullable ZonedDateTime releaseDate,
		@Nullable String[] nameAlias
) {
	
	/**
	 * capability constructor for v0.5
	 */
	public Song (int id, @Nonnull String name, @Nonnull SongUnit unit, @Nonnull Difficulties difficulties) {
		this(
				id, name, unit, difficulties,
				null, null, null, null,
				null, null, null, null
		);
	}
	
	/**
	 * !! outdated since v0.6: data type update
	 */
	@Override
	@Nonnull
	public String toString () {
		return String.format(
				"{\"id\":%d,\"name\":%s,\"unit\":%s,\"difficulties\":%s}",
				id,
				Converter.parseJSONString(name),
				unit,
				difficulties
		);
	}
	
}
