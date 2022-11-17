package cc.sukazyo.sekai_scores;

import javax.annotation.Nonnull;

public enum SongUnit {
	
	VSINGER("VIRTUAL SINGER"),
	LEO_NEED("Leo/need"),
	MORE_MORE_JUMP("MORE MORE JUMP!"),
	VIVID_BAD_SQUAD("Vivid BAD SQUAD"),
	WONDERLANDS_SHOWTIME("ワンダーランズ×ショウタイム"),
	NIGHTCORD_25JI("25時、ナイトコードで。"),
	OTHER("other"),
	SP("セカイ")
	;
	
	@Nonnull public final String name;
	
	SongUnit (@Nonnull String name) {
		this.name = name;
	}
	
}
