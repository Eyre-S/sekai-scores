package cc.sukazyo.sekai_scores;

import cc.sukazyo.sekai_scores.util.Converter;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

public record Difficulty(
		@Nonnull String id,
		@Nonnegative int level,
		@Nonnegative int noteCount,
		int levelPlus,
		int levelPlus_f,
		int levelPlus_p
) {
	
	public static final int NULL = -1;
	
	/**
	 * capability constructor for v0.5
	 */
	public Difficulty (@Nonnull String id, @Nonnegative int level, @Nonnegative int noteCount) {
		this(
				id, level, noteCount,
				NULL, NULL, NULL
		);
	}
	
	/**
	 * @since 0.4
	 */
	@Nonnull public String id () { return id; }
	
	/**
	 * The difficulty number of this difficulty map.
	 * <p>
	 * it is a number in range 1-36 in official prsk game.
	 * @since 0.2
	 */
	@Nonnegative public int level () { return level; }
	/**
	 * an alias of {@link #level} field.
	 * @since 0.3
	 * @see #level()
	 */
	@Nonnegative public int difficulty () { return level(); }
	
	/**
	 * The total note count in this difficulty map.
	 * @since 0.2
	 */
	@Nonnegative public int noteCount () { return noteCount; }
	
	@Override
	@Nonnull
	public String toString() {
		return String.format("{%s}", toStringSimple());
	}
	
	/**
	 * outdated since v0.6: data type update
	 */
	@Nonnull
	public String toStringSimple () {
		return String.format(
				"\"id\":%s,\"difficulty\":%d,\"notes\":%d",
				Converter.parseJSONString(id), level, noteCount
		);
	}
	
}
