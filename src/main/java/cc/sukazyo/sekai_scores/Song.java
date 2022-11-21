package cc.sukazyo.sekai_scores;

import cc.sukazyo.sekai_scores.util.Converter;

import javax.annotation.Nonnull;

public record Song(
		int id,
		@Nonnull String name,
		@Nonnull SongUnit unit,
		@Nonnull Difficulties difficulties
) {
	
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
