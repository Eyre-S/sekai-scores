package cc.sukazyo.sekai_scores;

import cc.sukazyo.sekai_scores.util.Converter;

import javax.annotation.Nonnull;

public record Song(
		@Nonnull String name,
		@Nonnull SongUnit unit,
		@Nonnull Difficulties difficulties
) {
	
	@Override
	@Nonnull
	public String toString () {
		return String.format(
				"{\"name\":\"%s\",\"unit\":%s,\"difficulties\":%s}",
				Converter.parseJSONString(name),
				unit,
				difficulties
		);
	}
	
}
