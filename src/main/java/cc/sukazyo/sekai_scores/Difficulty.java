package cc.sukazyo.sekai_scores;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

public record Difficulty(
		@Nonnegative int level,
		@Nonnegative int noteCount
) {
	
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
	
	@Nonnull
	public String toStringSimple () {
		return String.format(
				"\"difficulty\":%d,\"notes\":%d",
				level, noteCount
		);
	}
	
}
