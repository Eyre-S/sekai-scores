package cc.sukazyo.sekai_scores;

import cc.sukazyo.sekai_scores.util.Converter;

import javax.annotation.Nonnull;

public enum SongUnit {
	
	PIAPRO(1, "VIRTUAL SINGER"),
	LIGHT_SOUND(2, "Leo/need"),
	IDOL(3, "MORE MORE JUMP!"),
	STREET(4, "Vivid BAD SQUAD"),
	THEME_PARK(5, "ワンダーランズ×ショウタイム"),
	SCHOOL_REFUSAL(6, "25時、ナイトコードで。"),
	/**
	 * Just the "other" unit in game.
	 * @since 0.2
	 */
	OTHER(7, "other"),
	/**
	 * It collects that songs belongs to multiple units.
	 * <p>
	 * Like "Journey", "群青讃歌", it belongs to all of those units exists in game,
	 * and hard to assign those songs to those units exists. So this group was created,
	 * means the song belongs to "the whole セカイ".
	 * <p>
	 * This unit/group doesn't exist in the game.
	 * @since 0.2
	 */
	_X(-1, "セカイ")
	;
	
	public SongUnit getBySeq(int seq) {
		return switch (seq) {
			case 1 -> PIAPRO;
			case 2 -> LIGHT_SOUND;
			case 3 -> IDOL;
			case 4 -> STREET;
			case 5 -> THEME_PARK;
			case 6 -> SCHOOL_REFUSAL;
			case 7 -> OTHER;
			case -1 -> _X;
			default -> null;
		};
	}
	
	/**
	 * An id of this group/unit.
	 * <p>
	 * The value start at 1 for {@link #PIAPRO} which is <i>VIRTUAL SINGER</i>
	 * and increment according to the order of the groups/units in the game.
	 * <p>
	 * for some special group/unit, it has a special id:
	 * <ul>
	 *     <li>{@link #OTHER}: {@link 7}, as a special group that even exists in game</li>
	 *     <li>{@link #_X}: {@link -1}, as a group that doesn't exist in game</li>
	 * </ul>
	 * @since 0.3
	 */
	public final int seq;
	@Nonnull public final String name;
	
	SongUnit (int seq, @Nonnull String name) {
		this.seq = seq;
		this.name = name;
	}
	
	@Override
	@Nonnull
	public String toString() {
		return String.format(
				"{\"seq\":%d,\"fullname\":%s}",
				seq,
				Converter.parseJSONString(name)
		);
	}
	
}
