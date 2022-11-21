package cc.sukazyo.sekai_scores.db.project_sekai_colorful_stage;

import cc.sukazyo.sekai_scores.Difficulty;
import cc.sukazyo.sekai_scores.DifficultiesSekai;
import cc.sukazyo.sekai_scores.Song;
import cc.sukazyo.sekai_scores.SongUnit;

import static cc.sukazyo.sekai_scores.DifficultiesSekai.*;

public class Nightcord25ji {
	
	/** <a href="https://www.sekaipedia.org/wiki/Keitai_Renwa">...</a> */
	public static final Song Keitai_Renwa = new Song(
			61, "携帯恋話", SongUnit.NIGHTCORD_25JI,
			new DifficultiesSekai(
					new Difficulty(  EASY_NAME,  8,  204),
					new Difficulty(NORMAL_NAME, 12,  361),
					new Difficulty(  HARD_NAME, 18,  651),
					new Difficulty(EXPERT_NAME, 25,  981),
					new Difficulty(MASTER_NAME, 29, 1131)
			)
	);
	/** <a href="https://www.sekaipedia.org/wiki/Kuyamu_to_Kaite_Mirai">...</a> */
	public static final Song Kuyamu_to_Kaite_Mirai = new Song(
			60, "悔やむと書いてミライ", SongUnit.NIGHTCORD_25JI,
			new DifficultiesSekai(
					new Difficulty(  EASY_NAME, 8,  190),
					new Difficulty(NORMAL_NAME,13,  440),
					new Difficulty(  HARD_NAME,17,  541),
					new Difficulty(EXPERT_NAME,26,  815),
					new Difficulty(MASTER_NAME,30,  973)
			)
	);
	/** <a href="https://www.sekaipedia.org/wiki/ID_Smile">...</a> */
	public static final Song ID_SMILE = new Song(
			116, "アイディスマイル", SongUnit.NIGHTCORD_25JI,
			new DifficultiesSekai(
					new Difficulty(  EASY_NAME, 7,  263),
					new Difficulty(NORMAL_NAME,12,  439),
					new Difficulty(  HARD_NAME,17,  703),
					new Difficulty(EXPERT_NAME,25,  985),
					new Difficulty(MASTER_NAME,28, 1247)
			)
	);
	
}
