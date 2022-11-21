package cc.sukazyo.sekai_scores;

import cc.sukazyo.sekai_scores.util.Converter;

import javax.annotation.Nonnull;

public enum SongUnit {
	
	VSINGER(0, "VIRTUAL SINGER"),
	LEO_NEED(1, "Leo/need"),
	MORE_MORE_JUMP(2, "MORE MORE JUMP!"),
	VIVID_BAD_SQUAD(3, "Vivid BAD SQUAD"),
	WONDERLANDS_SHOWTIME(4, "ワンダーランズ×ショウタイム"),
	NIGHTCORD_25JI(5, "25時、ナイトコードで。"),
	/**
	 * Just the "other" unit in game.
	 * @since 0.2
	 */
	OTHER(-1, "other"),
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
	SP(-2, "セカイ")
	;
	
	/**
	 * An id of this group/unit.
	 * <p>
	 * The value start at 0 for {@link #VSINGER}
	 * and increment according to the order of the groups/units in the game.
	 * <p>
	 * for some special group/unit, it has a special id:
	 * <ul>
	 *     <li>{@link #OTHER}: {@link -1}, as a special group that even exists in game</li>
	 *     <li>{@link #SP}: {@link -2}, as a group that doesn't exist in game</li>
	 * </ul>
	 * @since 0.3
	 */
	public final int id;
	@Nonnull public final String name;
	
	SongUnit (int id, @Nonnull String name) {
		this.id = id;
		this.name = name;
	}
	
	@Override
	@Nonnull
	public String toString() {
		return String.format(
				"{\"id\":%d,\"fullname\":\"%s\"}",
				id,
				Converter.parseJSONString(name)
		);
	}
	
}
